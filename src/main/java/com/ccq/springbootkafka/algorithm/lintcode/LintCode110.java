package com.ccq.springbootkafka.algorithm.lintcode;

/********************************
 *** https://www.lintcode.com/problem/minimum-path-sum/description
 ***@Author chengchuanqiang
 ***@Date 2019/5/15 18:21
 ***@Version 1.0.0
 ********************************/
public class LintCode110 {

    public static void main(String[] args) {
        LintCode110 test = new LintCode110();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(test.minPathSum(grid));
    }

    /**
     * dp[i][j] = min(dp[i][j-1], dp[i-1][j]) + grid[i][j]
     *
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}
