package com.ccq.springbootkafka.algorithm.job20190616.lock;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/6/22 15:38
 */
public class Test {

    /**
     * 共享资源、临界资源
     */
    static int num = 0;
    private static MyLock myLock = new MyLock();

    public static void run() {
        for (int j = 0; j < 10000; j++) {
            try {
                myLock.lock();
                increase();
            } finally {
                myLock.unlock();
            }
        }
    }

    private static void increase() {
        num++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> run());

        Thread t2 = new Thread(() -> run());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(num);
    }

}
