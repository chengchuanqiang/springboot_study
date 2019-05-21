package com.ccq.springbootkafka.algorithm.dp.knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/4/25 14:03
 ***@Version 1.0.0
 ********************************/
public class Hybrid {
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
        System.out.println(hybrid());

    }

    private static int hybrid() {
        List<Goods> goodsList = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (s[i] == -1) {
                // 01 背包
                goodsList.add(new Goods(v[i], w[i], -1));
            } else if (s[i] == 0) {
                // 完全背包
                goodsList.add(new Goods(v[i], w[i], 0));
            } else {
                // 多重背包二进制优化转为01背包
                int ss = s[i];
                for (int j = 1; j <= ss; j = j * 2) {
                    ss = ss - j;
                    goodsList.add(new Goods(v[i] * j, w[i] * j, -1));
                }
                if (ss > 0) {
                    goodsList.add(new Goods(v[i] * ss, w[i] * ss, -1));
                }
            }
        }

        int[] dp = new int[V + 1];
        for (Goods goods : goodsList) {
            if (goods.s == -1) {
                for (int j = V; j >= goods.v; j--) {
                    dp[j] = Math.max(dp[j], dp[j - goods.v] + goods.w);
                }
            } else {
                for (int j = goods.v; j <= V; j++) {
                    dp[j] = Math.max(dp[j], dp[j - goods.v] + goods.w);
                }
            }

        }

        return dp[V];
    }

    static class Goods {
        int v;
        int w;
        int s;

        public Goods(int v, int w, int s) {
            this.v = v;
            this.w = w;
            this.s = s;
        }
    }
}


