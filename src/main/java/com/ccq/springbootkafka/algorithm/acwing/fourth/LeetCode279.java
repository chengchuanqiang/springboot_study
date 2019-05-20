package com.ccq.springbootkafka.algorithm.acwing.fourth;

/**
 * @Description: https://leetcode.com/problems/perfect-squares/
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 7:37
 */
public class LeetCode279 {

    public static void main(String[] args) {
        LeetCode279 test = new LeetCode279();
        System.out.println(test.numSquares(13));
    }

    /**
     * dp[i] 表示 i 最少被分成几个完全平方数之和
     * dp[i] = min{dp[i-j*j] + 1}  j*j = [0, i]
     *
     * @param n n
     * @return result
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

}
