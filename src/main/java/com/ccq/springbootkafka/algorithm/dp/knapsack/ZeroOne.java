package com.ccq.springbootkafka.algorithm.dp.knapsack;

import java.util.Scanner;

/********************************
 *** 01 背包问题
 *** dp[i][j] 使用前 i 个物品，装入体积为 j 背包中，最大的价值
 ***@Author chengchuanqiang
 ***@Date 2019/4/23 18:28
 ***@Version 1.0.0
 ********************************/
public class ZeroOne {
    /**
     * N个物品
     */
    private static int N;
    /**
     * 背包体积
     */
    private static int V;
    /**
     * 每个物品的体积
     */
    private static int[] v;
    /**
     * 每一个物品的价值
     */
    private static int[] w;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        V = input.nextInt();
        v = new int[N + 1];
        w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            v[i] = input.nextInt();
            w[i] = input.nextInt();
        }
        input.close();

        System.out.println(zeroOne1());
        System.out.println(zeroOne2());
    }

    /**
     * 使用二维数组
     *
     * @return 背包最大价值
     */
    private static int zeroOne1() {
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                if (j < v[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dp[i][V]);
        }
        return res;
    }

    /**
     * 一维数组
     *
     * @return 背包最大价值
     */
    private static int zeroOne2() {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        return dp[V];
    }
}
