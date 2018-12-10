package com.ccq.springbootkafka.algorithm.test20181210;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/10 17:49
 ***@Version 1.0.0
 ********************************/
public class I {
    public static void main(String[] args) {
        for (int i = 100; i <= 200; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isPrime(int num) {
        int n = (int) Math.sqrt(num) + 1;
        for (int i = 2; i <= n; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
