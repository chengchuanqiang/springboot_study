package com.ccq.springbootkafka.algorithm.job20190616.synchronizedTest;

/********************************
 *** synchronized 作用于实例方法
 ***@Author chengchuanqiang
 ***@Date 2019/6/19 17:03
 ***@Version 1.0.0
 ********************************/
public class AccountingSync implements Runnable {

    /**
     * 共享资源、临界资源
     */
    static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        AccountingSync accountingSync = new AccountingSync();
        Thread t1 = new Thread(accountingSync);
        Thread t2 = new Thread(accountingSync);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(num);
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    private void add() {
        synchronized (AccountingSync.class) {
            System.out.println(1 + 1);
        }
    }

    private synchronized void increase() {
        num++;
    }
}
