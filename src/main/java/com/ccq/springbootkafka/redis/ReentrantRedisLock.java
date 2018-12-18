/*
package com.ccq.springbootkafka.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

*/
/**
 * 该可重入锁的锁力度是实现到Application级别
 * 并没有实现到Thread级
 *//*

@Component
@Scope(scopeName = "singleton")
public class ReentrantRedisLock {

    private final String ipAndPort;

    private final RedisTemplate<String, String> template;

    public ReentrantRedisLock(RedisTemplate<String, String> template,
                              @Value("${server.port}") String port) {
        this.template = template;

        StringBuilder builder = new StringBuilder();
        Set<String> ips = InternetProtocol.getLocalIpv4();
        for (String ip : ips) {
            builder.append(ip).append(':').append(port).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);

        this.ipAndPort = builder.toString();
    }

    public boolean tryLock(String key, long expire) {
        return template.execute(new TryLockAction(key, ipAndPort, expire));
    }

    public boolean unLock(String key) {
        return template.execute(new UnLockAction(key, ipAndPort));
    }


    private static class TryLockAction implements RedisCallback<Boolean> {

        private String key;
        private String val;
        private long expire;

        private TryLockAction(String key, String val, long expire) {
            this.key = key;
            this.val = val;
            this.expire = expire;
        }

        @Override
        public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

            Object nativeConnection = connection.getNativeConnection();

            //该lua表示如果成功设置则返回"OK", 失败则返回null
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return 'OK' else return redis.call('set', KEYS[1], ARGV[1], 'PX', ARGV[2], 'NX') end";

            String result;

            if (nativeConnection instanceof Jedis) {
                Jedis jedis = (Jedis) nativeConnection;
                result = (String) jedis.eval(script, Collections.singletonList(key), Collections.singletonList(val));
            } else {
                JedisCluster cluster = (JedisCluster) nativeConnection;
                result = (String) cluster.eval(script, Collections.singletonList(key), Arrays.asList(val, String.valueOf(expire)));
            }

            if ("OK".equals(result)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static class UnLockAction implements RedisCallback<Boolean> {

        private String key;
        private String val;

        private UnLockAction(String key, String val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

            Object nativeConnection = connection.getNativeConnection();

            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

            Long result;

            if (nativeConnection instanceof Jedis) {
                Jedis jedis = (Jedis) nativeConnection;
                result = (Long) jedis.eval(script, Collections.singletonList(key), Collections.singletonList(val));
            } else {
                JedisCluster cluster = (JedisCluster) nativeConnection;
                result = (Long) cluster.eval(script, Collections.singletonList(key), Collections.singletonList(val));
            }

            if (1L == result) {
                return true;
            } else {
                return false;
            }
        }
    }
}
*/
