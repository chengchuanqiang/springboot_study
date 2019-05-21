package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Arrays;

/********************************
 *** https://leetcode.com/problems/jump-game/
 ***@Author chengchuanqiang
 ***@Date 2019/5/13 10:15
 ***@Version 1.0.0
 ********************************/
public class LeetCode55 {

    public static void main(String[] args) {
        LeetCode55 test = new LeetCode55();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(test.canJump(nums));
        int[] nums1 = {3, 2, 1, 0, 4};
        System.out.println(test.canJump(nums1));

    }

    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        Arrays.fill(dp, false);
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[nums.length - 1];
    }
}
