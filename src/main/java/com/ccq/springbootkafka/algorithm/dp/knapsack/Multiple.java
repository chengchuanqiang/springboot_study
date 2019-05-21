package com.ccq.springbootkafka.algorithm.dp.knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/********************************
 *** 多重背包，物品的数量有限
 ***@Author chengchuanqiang
 ***@Date 2019/4/25 10:19
 ***@Version 1.0.0
 ********************************/
public class Multiple {
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
    /**
     * 每一个物品的个数
     */
    private static int[] s;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        V = input.nextInt();
        v = new int[N + 1];
        w = new int[N + 1];
        s = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            v[i] = input.nextInt();
            w[i] = input.nextInt();
            s[i] = input.nextInt();
        }
        input.close();
        System.out.println(multiple1());
        System.out.println(multiple2());

    }

    private static int multiple1() {
        int[][] dp = new int[N + 1][V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                int num = j / v[i];
                for (int k = 0; k <= num && k <= s[i]; k++) {
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

    /**
     * 二进制优化，转化为01背包
     * @return 最大价值
     */
    private static int multiple2() {
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int ss = s[i];
            for (int j = 1; j <= ss; j = j * 2) {
                ss = ss - j;
                goodsList.add(new Goods(v[i] * j, w[i] * j));
            }

            if (ss > 0) {
                goodsList.add(new Goods(v[i] * ss, w[i] * ss));
            }
        }

        int[] dp = new int[V + 1];
        for (Goods goods : goodsList) {
            for (int j = V; j >= goods.v; j--) {
                dp[j] = Math.max(dp[j], dp[j - goods.v] + goods.w);
            }
        }

        return dp[V];
    }

    static class Goods {
        int v;
        int w;

        public Goods(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}


