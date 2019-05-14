package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 *** https://leetcode.com/problems/unique-paths-ii/description/
 ***@Author chengchuanqiang
 ***@Date 2019/5/14 9:43
 ***@Version 1.0.0
 ********************************/
public class LeetCode63 {

    public static void main(String[] args) {
        LeetCode63 test = new LeetCode63();
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(test.uniquePathsWithObstacles(obstacleGrid));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        if (row == 0) {
            return 0;
        }
        int col = obstacleGrid[0].length;
        if (col == 0) {
            return 0;
        }

        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = 0;
                if (i - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }
}
