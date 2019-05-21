package com.ccq.springbootkafka.algorithm.lintcode;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/5/16 10:13
 ***@Version 1.0.0
 ********************************/
public class LintCode534 {

    /**
     * case1: 不选0
     * case2：不选n-1
     *
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }

        // case1：不选0
        int[][] dp = new int[len + 1][2];
        for (int i = 2; i <= len; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[i - 1];
        }
        int res = Math.max(dp[len][0], dp[len][1]);

        // case2：不选n-1
        dp = new int[len + 1][2];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[i - 1];
        }

        return Math.max(res, Math.max(dp[len - 1][0], dp[len - 1][1]));
    }

    public static void main(String[] args) {
        LintCode534 test = new LintCode534();
        int[] nums = {3, 6, 4};
        System.out.println(test.houseRobber2(nums));
    }
}
