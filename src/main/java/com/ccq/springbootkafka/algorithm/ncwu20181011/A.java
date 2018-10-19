package com.ccq.springbootkafka.algorithm.ncwu20181011;

import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/10/11 14:55
 ***@Version 1.0.0
 ********************************/
public class A {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        StringBuilder str;
        long sum;
        while (input.hasNext()) {
            n = input.nextInt();
            char x = input.next().charAt(0);
//            long s = System.currentTimeMillis();

            str = new StringBuilder();
            for (int i = 0; i <= n; i++) {
                str.append(String.valueOf(i));
            }
            sum = str.chars().filter(c -> c == x).count();
            System.out.println(sum);
//            long t = System.currentTimeMillis();
//            System.out.println((t - s) / 1000.0 + "s");

        }
        input.close();
    }

}
