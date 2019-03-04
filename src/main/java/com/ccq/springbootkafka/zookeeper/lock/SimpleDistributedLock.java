package com.ccq.springbootkafka.zookeeper.lock;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/3/4 9:55
 ***@Version 1.0.0
 ********************************/
public class SimpleDistributedLock extends BaseDistributedLock implements DistributedLock {


    private final String basePath;
    private static final String LOCK_NAME = "lock-";
    private String ourLockPath;

    public SimpleDistributedLock(ZkClient client, String basePath) {
        super(client, basePath, LOCK_NAME);
        this.basePath = basePath;
    }

    private boolean internalLock(long time, TimeUnit unit) throws Exception {
        ourLockPath = attemptLock(time, unit);
        return ourLockPath != null;
    }

    @Override
    public void acquire() throws Exception {
        if (!internalLock(-1, null)) {
            throw new IOException("连接丢失!在路径:'" + basePath + "'下不能获取锁!");
        }
    }

    @Override
    public boolean acquire(long timeout, TimeUnit timeUnit) throws Exception {
        return internalLock(timeout, timeUnit);
    }

    @Override
    public void release() throws Exception{
        releaseLock(ourLockPath);
    }
}
