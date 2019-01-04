package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Arrays;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/1/3 9:41
 ***@Version 1.0.0
 ********************************/
public class LeetCode473 {

    public boolean makesquare(int[] nums) {
        if (null == nums || nums.length < 4) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % 4 != 0) {
            return false;
        }
        int len = sum / 4;
        Arrays.sort(nums);
        return dfs(nums, new int[4], len, nums.length - 1);
    }

    private boolean dfs(int[] nums, int[] res, int len, int index) {
        if (index < 0) {
            return res[0] == len && res[1] == len && res[2] == len && res[3] == len;
        }

        for (int i = 0; i < 4; i++) {
            if (res[i] + nums[index] <= len) {
                res[i] += nums[index];
                if (dfs(nums, res, len, index - 1)) {
                    return true;
                }
                res[i] -= nums[index];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode473 test = new LeetCode473();

        System.out.println(test.makesquare(new int[]{1, 1, 2, 2, 2}));
        System.out.println(test.makesquare(new int[]{3, 3, 3, 3, 4}));
    }

}
