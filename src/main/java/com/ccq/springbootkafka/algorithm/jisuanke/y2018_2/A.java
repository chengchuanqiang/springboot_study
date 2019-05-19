package com.ccq.springbootkafka.algorithm.jisuanke.y2018_2;

import java.util.Scanner;

/**
 * @Description: https://nanti.jisuanke.com/t/A1701
 * @Author: ChengChuanQiang
 * @Date: 2019/5/18 14:02
 */
public class A {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while ((T--) > 0) {
            int n = input.nextInt();
            int d = input.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = input.nextInt();
            }
            int[] dp = new int[n];
            int res = 1;
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (Math.abs(a[i] - a[j]) <= d) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                if (dp[i] > res) {
                    res = dp[i];
                }
            }
            System.out.println(res);
        }
    }
}
