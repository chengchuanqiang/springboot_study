package com.ccq.springbootkafka.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.Arrays;
import java.util.Collections;

/********************************
 *** Redis 分布式锁实现
 ***@Author chengchuanqiang
 ***@Date 2018/9/17 9:35
 ***@Version 1.0.0
 ********************************/
@Component
public class RedisLockPlus {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long UNLOCK_SUCCESS = 1L;

    /**
     * 加锁的Lua脚本
     */
    private static final String LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('set', KEYS[1], ARGV[1], 'PX', ARGV[2]) else return redis.call('set', KEYS[1], ARGV[1], 'PX', ARGV[2], 'NX') end";


    /**
     * 解锁的Lua脚本
     */
    private static final String UNLOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockPlus.class);

    /**
     * redis分布式锁实现
     */

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 获取锁 重入锁
     *
     * @param key        锁
     * @param requireId  请求唯一标识
     * @param expireTime 超期时间
     * @return 获取锁是否成功
     */
    public boolean lock(String key, String requireId, long expireTime) {

        try {
            // 使用Lua脚本达到获取锁的原子操作
            String result = redisTemplate.execute((RedisCallback<String>) connection -> {
                Object nativeConnection = connection.getNativeConnection();
                if (nativeConnection instanceof JedisCluster) {
                    return (String) ((JedisCluster) nativeConnection).eval(LOCK_LUA_SCRIPT, Collections.singletonList(key), Arrays.asList(requireId, String.valueOf(expireTime)));
                } else if (nativeConnection instanceof Jedis) {
                    return (String) ((Jedis) nativeConnection).eval(LOCK_LUA_SCRIPT, Collections.singletonList(key), Arrays.asList(requireId, String.valueOf(expireTime)));
                }
                return "";
            });
            return LOCK_SUCCESS.equals(result);
        } catch (Exception e) {
            LOGGER.info("get redis lock exception, {}", e.getMessage());
        }
        return false;
    }


    /**
     * 释放锁
     *
     * @param key       锁
     * @param requireId 请求唯一标识
     * @return 释放锁是否成功
     */
    public boolean unLock(String key, String requireId) {
        try {
            // 释放锁使用Lua脚本达到释放锁的原子操作
            Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
                Object nativeConnection = connection.getNativeConnection();
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA_SCRIPT, Collections.singletonList(key), Collections.singletonList(requireId));
                } else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA_SCRIPT, Collections.singletonList(key), Collections.singletonList(requireId));
                }
                return 0L;
            });
            return UNLOCK_SUCCESS.equals(result);
        } catch (Exception e) {
            LOGGER.info("release redis lock exception, {}", e.getMessage());
        }
        return false;
    }
}
