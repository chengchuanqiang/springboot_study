package com.ccq.springbootkafka.zookeeper.lock;

import org.I0Itec.zkclient.ZkClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/3/4 10:01
 ***@Version 1.0.0
 ********************************/
public class LockTest {
    public static void main(String[] args) throws Exception {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 3000);
        SimpleDistributedLock simple = new SimpleDistributedLock(zkClient, "/locker");

        // 请求总数
        int clientTotal = 10;

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            final int num = i;
            executorService.submit(() -> {
                try {
                    boolean acquire = simple.acquire(1000, TimeUnit.MILLISECONDS);
                    String date = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss").format(new Date());
                    if (acquire) {
                        System.out.println("=================get lock success " + num + ":::::::" + date);
                    } else {
                        System.out.println("=================get lock fail " + num + ":::::::" + date);
                    }
                    Thread.sleep(300);
                } catch (Exception e) {
                    System.out.println("异常了！异常了:" + e.getMessage());
                } finally {
                    try {
                        simple.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("=================\r\n");
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
    }
}
