package com.ccq.springbootkafka.algorithm.jisuanke.y2018_1;

import java.util.Scanner;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/18 13:11
 */
public class A {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        long p = input.nextLong();
        long s = input.nextLong();

        int l = 1;
        int r = 20005;
        while (l <= r) {
            int m = (l + r) >> 1;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] > m) {
                    sum = sum + (a[i] - m) * p;
                }
            }
            if (sum > s) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        System.out.println(l);
    }

}
