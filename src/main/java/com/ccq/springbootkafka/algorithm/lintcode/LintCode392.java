package com.ccq.springbootkafka.algorithm.lintcode;

/********************************
 *** https://www.lintcode.com/problem/house-robber/description
 ***@Author chengchuanqiang
 ***@Date 2019/5/16 9:56
 ***@Version 1.0.0
 ********************************/
public class LintCode392 {

    /**
     * dp[i][0] = max(dp[i-1][1], dp[i-1][0])
     * dp[i][1] = dp[i-1][0] + A[i-1]
     *
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (null == A || A.length == 0) {
            return 0;
        }
        long[][] dp = new long[A.length + 1][2];

        for (int i = 1; i <= A.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + A[i - 1];
        }
        return Math.max(dp[A.length][0], dp[A.length][1]);
    }

    public static void main(String[] args) {
        LintCode392 test = new LintCode392();
        int[] nums = {3, 8, 4};
        System.out.println(test.houseRobber(nums));
    }

}
