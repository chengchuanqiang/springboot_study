package com.ccq.springbootkafka.algorithm.job20190616.threadsafe;

/********************************
 *** 实例变量：单例时，非线程安全；非单例时，线程安全
 *** 实例变量是实例对象私有的，系统只存在一个实例对象，则在多线程环境下，如果值改变后，则其他对象均可见，故线程非安全；
 *** 如果，每个线程都在不同的实例对象中执行，则对象与对象间的修改互不影响，故线程安全
 ***@Author chengchuanqiang
 ***@Date 2019/6/17 11:41
 ***@Version 1.0.0
 ********************************/
public class InstanceValueThread implements Runnable {

    private int num;

    @Override
    public void run() {
        num = 3;
        System.out.println("当前线程是：" + Thread.currentThread().getName() + ", num = " + num);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 5;
        System.out.println("当前线程是：" + Thread.currentThread().getName() + ", num * 2 = " + num * 2);
    }

    public static void main(String[] args) {
//        unSafeThread();

        safeThread();
    }

    private static void unSafeThread() {
        InstanceValueThread thread = new InstanceValueThread();

        for (int i = 1; i <= 50; i++) {
            new Thread(thread, "Thread-" + i).start();
        }
    }

    private static void safeThread() {
        for (int i = 1; i <= 50; i++) {
            new Thread(new InstanceValueThread(), "Thread-" + i).start();
        }
    }
}
