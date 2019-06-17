package com.ccq.springbootkafka.algorithm.job20190616.threadsafe;

/********************************
 *** 静态变量：线程非安全
 *** 静态变量也称类变量，属于类对象所有，位于方法区，为所有对象共享，共享一份内存，一旦值被修改，则其他对象均对修改可见，故线程非安全
 ***@Author chengchuanqiang
 ***@Date 2019/6/17 11:26
 ***@Version 1.0.0
 ********************************/
public class StaticValueThread implements Runnable {

    private static int num;

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

        StaticValueThread thread = new StaticValueThread();

        for (int i = 1; i <= 50; i++) {
            new Thread(thread, "Thread-" + i).start();
        }

    }

}
