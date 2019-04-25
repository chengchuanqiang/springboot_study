package com.ccq.springbootkafka.algorithm.dp.knapsack;

import java.util.Scanner;

/********************************
 *** 二维费用背包
 ***@Author chengchuanqiang
 ***@Date 2019/4/25 14:45
 ***@Version 1.0.0
 ********************************/
public class TowDimensionalCost {
    /**
     * N个物品
     */
    private static int N;
    /**
     * 背包体积
     */
    private static int V;
    /**
     * 背包承受最大的重量
     */
    private static int M;
    /**
     * 每个物品的体积
     */
    private static int[] v;
    /**
     * 每一个物品的重量
     */
    private static int[] m;
    /**
     * 每一个物品的价值
     */
    private static int[] w;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        V = input.nextInt();
        M = input.nextInt();
        v = new int[N + 1];
        m = new int[N + 1];
        w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            v[i] = input.nextInt();
            m[i] = input.nextInt();
            w[i] = input.nextInt();
        }
        input.close();

        System.out.println(towDimensionalCost1());
        System.out.println(towDimensionalCost2());
    }

    private static int towDimensionalCost1() {
        int[][][] dp = new int[N + 1][V + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= M; k++) {
                    if (j < v[i] || k < m[i]) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - v[i]][k - m[i]] + w[i]);
                    }
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dp[i][V][M]);
        }
        return res;
    }


    private static int towDimensionalCost2() {
        int[][] dp = new int[V + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                for (int k = M; k >= m[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - v[i]][k - m[i]] + w[i]);
                }
            }
        }
        return dp[V][M];
    }

}
