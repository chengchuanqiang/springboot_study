package com.ccq.springbootkafka.algorithm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 最长公共子序列
 * @Author: ChengChuanQiang
 * @Date: 2019/3/24 22:23
 */
public class LCS {

    public static void main(String[] args) {
//        String x = "ABCBDAB";
//        String y = "BDCABA";

        String x = "1234";
        String y = "123564";

        System.out.println(LcsDfs(x, y, 0, 0));
        System.out.println(LcsStr(x, y, 0, 0));


        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j < y.length(); j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        System.out.println(LcsDfs(x, y, 0, 0, dp));

        System.out.println(Lcs(x, y));
    }

    /**
     * 递归实现 自顶向下
     *
     * @param x 字符串x
     * @param y 字符串y
     * @param i x的index
     * @param j y的index
     * @return result
     */
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

    private static String LcsStr(String x, String y, int i, int j) {
        if (i == x.length() || j == y.length()) {
            return "";
        }

        if (x.charAt(i) == y.charAt(j)) {
            return LcsStr(x, y, i + 1, j + 1) + x.charAt(i);
        } else {
            return LcsStr(x, y, i + 1, j).length() > LcsStr(x, y, i, j + 1).length() ? LcsStr(x, y, i + 1, j) : LcsStr(x, y, i, j + 1);
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
        int[][] mark = new int[x.length() + 1][y.length() + 1];

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
                    // 对角线
                    mark[i][j] = 1;
                } else if (dp[i][j - 1] > dp[i - 1][j]) {
                    dp[i][j] = dp[i][j - 1];
                    // 左边
                    mark[i][j] = 2;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    // 上边
                    mark[i][j] = 3;
                }
            }
        }
        List<Character> path = new ArrayList<>();
        printPath(x.length(), y.length(), x, y, mark, path);
        for (Character c : path) {
            System.out.print(c + " -> ");
        }
        System.out.println();

        return dp[x.length()][y.length()];
    }

    private static void printPath(int i, int j, String x, String y, int[][] mark, List<Character> path) {
        if (i == 0 || j == 0) {
            return;
        }

        if (mark[i][j] == 1) {
            printPath(i - 1, j - 1, x, y, mark, path);
            path.add(x.charAt(i - 1));
        } else if (mark[i][j] == 2) {
            // 左边
            printPath(i, j - 1, x, y, mark, path);
        } else {
            // 上边
            printPath(i - 1, j, x, y, mark, path);
        }
    }

}
