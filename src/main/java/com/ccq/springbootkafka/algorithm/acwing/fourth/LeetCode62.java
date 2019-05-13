package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 *** https://leetcode.com/problems/unique-paths/description/
 ***@Author chengchuanqiang
 ***@Date 2019/5/13 10:01
 ***@Version 1.0.0
 ********************************/
public class LeetCode62 {

    public static void main(String[] args) {
        LeetCode62 test = new LeetCode62();
        System.out.println(test.uniquePaths(7, 3));
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
