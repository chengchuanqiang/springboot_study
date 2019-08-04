package com.ccq.springbootkafka.algorithm.job20190616.synchronizedTest;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/6/22 17:10
 */
public class Sync {

    private static int i = 0;

    public static synchronized void add() {
        i++;
    }

    public static void main(String[] args) {
        add();
    }

    public void xxx() {
        synchronized (Sync.class) {
            i = 0;
            System.out.println("1111");
        }
        System.out.println(1 + 1);
    }

}
