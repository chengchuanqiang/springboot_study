package com.ccq.springbootkafka.algorithm.lanqiaobei01;

import java.util.Scanner;

/********************************
 *** Fibonacci数列
 *** http://www.ncwu.club/problem.php?id=4498
 ***@Author chengchuanqiang
 ***@Date 2019/3/10 10:29
 ***@Version 1.0.0
 ********************************/
public class Fibonacci {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] fib = new int[1000001];
        fib[0] = 0;
        fib[1] = 1;
        fib[2] = 1;
        for (int i = 3; i < 1000000; i++) {
            fib[i] = (fib[i - 1] + fib[i - 2]) % 10007;
        }
        while (input.hasNext()) {
            int n = input.nextInt();
            System.out.println(fib[n] % 10007);
        }
    }
}
