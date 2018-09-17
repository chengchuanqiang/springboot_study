package com.ccq.springbootkafka.redis;

import com.ccq.springbootkafka.SpringbootkafkaApplication;
import com.ccq.springbootkafka.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/********************************
 *** redis锁测试类
 ***@Author chengchuanqiang
 ***@Date 2018/9/14 17:34
 ***@Version 1.0.0
 ********************************/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootkafkaApplication.class)
@WebAppConfiguration
public class RedisLockTest {

    private static final String KEY = "worksheet_schedule_lock";
    private static final long EXPIRE_TIME = 60 * 1000;

    @Autowired
    private RedisLockPlus redisLock;

    @Test
    public void testLock1() throws InterruptedException {
        String requireId = CommonUtil.getMACAddress();
        if (redisLock.lock(KEY, requireId, EXPIRE_TIME)) {
            System.out.println("获取锁成功");
        } else {
            System.out.println("获取锁失败");
        }

        Thread.sleep(1000);

        if (redisLock.lock(KEY, requireId , EXPIRE_TIME)) {
            System.out.println("获取锁成功");
        } else {
            System.out.println("获取锁失败");
        }

        Thread.sleep(1000);

        if(redisLock.unLock(KEY, requireId)){
            System.out.println("释放锁成功");
        }else{
            System.out.println("释放锁失败");
        }
    }

    @Test
    public void testLock222() throws InterruptedException {
        String requireId = CommonUtil.getMACAddress();
        System.out.println(requireId);
        if (redisLock.lock(KEY, requireId, EXPIRE_TIME)) {
            System.out.println("获取锁成功");
        } else {
            System.out.println("获取锁失败");
        }

        Thread.sleep(10000);

        if (redisLock.unLock(KEY, requireId + "1")) {
            System.out.println("释放锁成功");
        } else {
            System.out.println("释放锁失败");
        }


        if (redisLock.unLock(KEY, requireId)) {
            System.out.println("释放锁成功");
        } else {
            System.out.println("释放锁失败");
        }
    }


    /**
     * 多线程并发测试
     */
    @Test
    public void testLock2() throws InterruptedException {

        // 请求总数
        int clientTotal = 1000;
        // 同时并发执行的线程数
        int threadTotal = 100;

        String requireId = CommonUtil.getMACAddress();

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int num = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    if (redisLock.lock(KEY, requireId + num, EXPIRE_TIME)) {
                        System.out.println("=================get lock success " + requireId + num);
                    } else {
                        System.out.println("get lock fail " + requireId + num);
                    }
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("异常");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }


}

