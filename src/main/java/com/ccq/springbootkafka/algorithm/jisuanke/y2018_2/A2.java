package com.ccq.springbootkafka.algorithm.jisuanke.y2018_2;

import java.util.Scanner;

/**
 * @Description: https://nanti.jisuanke.com/t/A1701
 * 题解：https://blog.csdn.net/m0_38013346/article/details/80305150
 * @Author: ChengChuanQiang
 * @Date: 2019/5/19 10:20
 */
public class A2 {

    private static int maxn = 100000;
    private static int[] max;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while ((T--) > 0) {
            max = new int[maxn + 5];
            int n = input.nextInt();
            int d = input.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = input.nextInt();
            }
            int[] dp = new int[n];
            int res = 1;
            for (int i = 0; i < n; i++) {
                dp[i] = getMax(Math.max(0, a[i] - d), Math.min(a[i] + d, maxn)) + 1;
                max[a[i]] = dp[i];
                res = Math.max(res, dp[i]);
            }

            System.out.println(res);
        }

    }

    private static int getMax(int l, int r) {
        int ans = 0;
        for (int i = l; i <= r; i++) {
            ans = Math.max(ans, max[i]);
        }
        return ans;
    }
}
