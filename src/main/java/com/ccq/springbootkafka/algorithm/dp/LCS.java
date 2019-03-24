package com.ccq.springbootkafka.algorithm.dp;

/**
 * @Description: 最长公共子序列
 * @Author: ChengChuanQiang
 * @Date: 2019/3/24 22:23
 */
public class LCS {

    public static void main(String[] args) {
        String x = "ABCBDAB";
        String y = "BDCABA";

        System.out.println(LcsDfs(x, y, 0, 0));
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j < y.length(); j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        System.out.println(LcsDfs(x, y, 0, 0, dp));

        System.out.println(Lcs(x, y));
    }

    private static int LcsDfs(String x, String y, int i, int j) {

        if (i == x.length() || j == y.length()) {
            return 0;
        }

        if (x.charAt(i) == y.charAt(j)) {
            return LcsDfs(x, y, i + 1, j + 1) + 1;
        } else {
            return Math.max(LcsDfs(x, y, i + 1, j), LcsDfs(x, y, i, j + 1));
        }

    }

    private static int LcsDfs(String x, String y, int i, int j, int[][] dp) {

        if (i == x.length() || j == y.length()) {
            return 0;
        }

        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }

        if (x.charAt(i) == y.charAt(j)) {
            dp[i][j] = LcsDfs(x, y, i + 1, j + 1) + 1;
        } else {
            dp[i][j] = Math.max(LcsDfs(x, y, i + 1, j, dp), LcsDfs(x, y, i, j + 1, dp));
        }

        return dp[i][j];
    }

    private static int Lcs(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= x.length(); i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < y.length(); j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[x.length()][y.length()];
    }

}
