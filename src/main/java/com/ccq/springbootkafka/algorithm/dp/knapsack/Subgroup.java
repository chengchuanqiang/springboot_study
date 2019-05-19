package com.ccq.springbootkafka.algorithm.dp.knapsack;

import java.util.Scanner;

/********************************
 *** 分组背包
 ***@Author chengchuanqiang
 ***@Date 2019/4/26 16:24
 ***@Version 1.0.0
 ********************************/
public class Subgroup {
    /**
     * N个物品
     */
    private static int N;
    /**
     * 背包体积
     */
    private static int V;
    /**
     * v[i][j] 第i组物品第j个的体积值
     */
    private static int[][] v;
    /**
     * w[i][j] 第i组物品第j个的价值
     */
    private static int[][] w;
    /**
     * s[i] 第i组物品的个数
     */
    private static int[] s;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        V = input.nextInt();
        s = new int[N + 1];
        v = new int[N + 1][];
        w = new int[N + 1][];

        for (int i = 1; i <= N; i++) {
            s[i] = input.nextInt();
            v[i] = new int[s[i] + 1];
            w[i] = new int[s[i] + 1];
            for (int j = 1; j <= s[i]; j++) {
                v[i][j] = input.nextInt();
                w[i][j] = input.nextInt();
            }
        }
        input.close();
        System.out.println(subgroup2());
    }

    private static int subgroup1() {
        int[][] dp = new int[N + 1][V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 0; k <= s[i]; k++) {
                    if (j >= v[i][k]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i][k]] + w[i][k]);
                    }
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dp[i][V]);
        }
        return res;
    }

    private static int subgroup2() {
        int[] dp = new int[V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= 0; j--) {
                for (int k = 0; k <= s[i]; k++) {
                    if (j >= v[i][k]) {
                        dp[j] = Math.max(dp[j], dp[j - v[i][k]] + w[i][k]);
                    }
                }
            }
        }

        return dp[V];
    }
}
