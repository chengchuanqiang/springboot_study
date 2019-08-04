package com.ccq.springbootkafka.algorithm.job20190616.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 死锁
 * @Author: ChengChuanQiang
 * @Date: 2019/6/23 8:29
 */
public class DeadLock {

    public Object obj1 = new Object();
    public Object obj2 = new Object();

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> deadLock.a());
        executorService.execute(() -> deadLock.b());
    }

    public void a() {
        synchronized (obj1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("a");
            }
        }
    }

    public void b() {
        synchronized (obj2) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1) {
                System.out.println("b");
            }
        }
    }

}
