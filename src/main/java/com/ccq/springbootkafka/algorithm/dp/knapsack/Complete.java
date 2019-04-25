package com.ccq.springbootkafka.algorithm.dp.knapsack;

import java.util.Scanner;

/********************************
 *** 完全背包
 *** dp[i][j] 前i个物品，可以无限的重复装，装入体积为j 背包，最大的价值
 ***@Author chengchuanqiang
 ***@Date 2019/4/24 10:17
 ***@Version 1.0.0
 ********************************/
public class Complete {
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

        System.out.println(complete1());
        System.out.println(complete2());

    }

    private static int complete1() {
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                int num = j / v[i];
                for (int k = 0; k <= num; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dp[i][V]);
        }
        return res;
    }

    private static int complete2() {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = v[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        return dp[V];
    }
}
