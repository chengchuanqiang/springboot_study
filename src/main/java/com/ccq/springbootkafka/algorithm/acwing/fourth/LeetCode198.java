package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Arrays;

/********************************
 *** 使用 DFS -> 记忆化DFS -> DP
 ***     O(2^N) -> O(N) -> O(N)
 ***@Author chengchuanqiang
 ***@Date 2019/4/29 9:56
 ***@Version 1.0.0
 ********************************/
public class LeetCode198 {

    public static void main(String[] args) {
        LeetCode198 test = new LeetCode198();
        int[] nums = {1, 20, 3};
        System.out.println(test.rob(nums));
    }

    public int rob1(int[] nums) {
        dfs1(0, nums, 0);
        return ans;
    }

    int ans = 0;

    private void dfs1(int i, int[] nums, int sum) {
        if (i >= nums.length) {
            if (sum > ans) {
                ans = sum;
            }
            return;
        }
        dfs1(i + 1, nums, sum);
        dfs1(i + 2, nums, sum + nums[i]);
    }

    public int rob2(int[] nums) {
        mark = new int[nums.length];
        Arrays.fill(mark, -1);
        return dfs2(0, nums);
    }

    int[] mark;

    private int dfs2(int i, int[] nums) {
        if (i >= nums.length) {
            return 0;
        }

        if (mark[i] != -1) {
            return mark[i];
        }

        mark[i] = Math.max(dfs2(i + 1, nums), dfs2(i + 2, nums) + nums[i]);
        return mark[i];
    }

    public int rob(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dp[i] = nums[0];
            } else if (i == 1) {
                dp[i] = Math.max(nums[0], nums[i]);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
        }
        return dp[nums.length - 1];
    }

}
