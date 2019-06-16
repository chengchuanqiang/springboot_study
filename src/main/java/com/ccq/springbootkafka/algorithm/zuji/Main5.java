package com.ccq.springbootkafka.algorithm.zuji;

import java.util.Scanner;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/4/14 11:29
 */
public class Main5 {

    private static final int INF = 10001;
    private static int n;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        map = new int[n][n];
        dp = new int[1 << n + 1][n + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = input.nextInt();
                if (x == 0) {
                    map[i][j] = 10001;
                } else {
                    map[i][j] = x;
                }
            }
        }
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int s, int v) {
        if (dp[s][v] >= 0) {
            return dp[s][v];
        }
        if ((s == (1 << n) - 1) && (v == 0)) {
            return dp[s][v] = 0;
        }
        int res = INF;
        for (int u = 0; u < n; u++) {
            if (((s >> u) & 1) == 0) {
                res = Math.min(res, dfs(s | 1 << u, u) + map[v][u]);
            }
        }
        return dp[s][v] = res;
    }
}
