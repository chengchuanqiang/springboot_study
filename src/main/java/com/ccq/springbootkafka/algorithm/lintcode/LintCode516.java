package com.ccq.springbootkafka.algorithm.lintcode;

/********************************
 *** https://www.lintcode.com/problem/paint-house-ii/description
 ***@Author chengchuanqiang
 ***@Date 2019/5/15 9:50
 ***@Version 1.0.0
 ********************************/
public class LintCode516 {

    /**
     * dp[i][k]
     * = min{dp[i-1][0], dp[i-1][1]....dp[i-1][k-1], dp[i-1][k+1]....,dp[i-1][K-1]} + costs[i-1][k]
     * = min{dp[i-1][j]} + costs[i-1][k] --- j != k && j = [0, K-1] && k = [0, K-1]
     *
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        if (null == costs || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int K = costs[0].length;
        int[][] dp = new int[n + 1][K];
        for (int i = 0; i < K; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {

            // 优化，先求出最小值和次小值
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            int j1 = -1;
            int j2 = -1;

            // get min1 && min2
            for (int j = 0; j < K; j++) {
                if (dp[i - 1][j] < min1) {
                    min2 = min1;
                    j2 = j1;
                    min1 = dp[i - 1][j];
                    j1 = j;
                } else if (dp[i - 1][j] < min2) {
                    min2 = dp[i - 1][j];
                    j2 = j;
                }
            }

            for (int j = 0; j < K; j++) {
                if (j != j1) {
                    dp[i][j] = min1 + costs[i - 1][j];
                } else {
                    dp[i][j] = min2 + costs[i - 1][j];
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < K; i++) {
            if (dp[n][i] < res) {
                res = dp[n][i];
            }
        }
        return res;
    }

    public int minCostII1(int[][] costs) {
        if (null == costs || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int K = costs[0].length;
        int[][] dp = new int[n + 1][K];
        for (int i = 0; i < K; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < K; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < K; k++) {
                    if (k != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < K; i++) {
            if (dp[n][i] < res) {
                res = dp[n][i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LintCode516 test = new LintCode516();
        int[][] costs = {{14, 2, 11}, {11, 14, 5}, {14, 3, 10}};
        System.out.println(test.minCostII(costs));
    }
}
