package com.ccq.springbootkafka.algorithm.job20190616.threadsafe;

/********************************
 *** 局部变量：线程安全
 *** 每个线程执行时都会把局部变量放在各自的栈帧的内存空间中，
 ***@Author chengchuanqiang
 ***@Date 2019/6/17 14:05
 ***@Version 1.0.0
 ********************************/
public class LocalValueThread implements Runnable {

    @Override
    public void run() {
        int num = 3;
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
        LocalValueThread thread = new LocalValueThread();

        for (int i = 1; i <= 50; i++) {
            new Thread(thread, "Thread-" + i).start();
        }
    }
}
