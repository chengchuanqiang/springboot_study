package com.ccq.springbootkafka.zookeeper.lock;

import java.util.concurrent.TimeUnit;

/********************************
 *** 分布式锁接口
 ***@Author chengchuanqiang
 ***@Date 2019/3/1 18:34
 ***@Version 1.0.0
 ********************************/
public interface DistributedLock {

    /**
     * 获取锁，如果没有获取到锁，就一直在等待
     *
     * @throws Exception 异常
     */
    void acquire() throws Exception;

    /**
     * 获取锁，带有超时时间
     *
     * @param timeout  超时时间
     * @param timeUnit time时间单位
     * @return true|false
     * @throws Exception 异常
     */

    boolean acquire(long timeout, TimeUnit timeUnit) throws Exception;

    /**
     * 释放锁
     *
     * @throws Exception 异常
     */
    void release() throws Exception;
}
