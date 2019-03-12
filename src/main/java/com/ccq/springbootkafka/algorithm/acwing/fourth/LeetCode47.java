package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************
 *** 重复数字的全排列
 ***@Author chengchuanqiang
 ***@Date 2018/12/19 19:32
 ***@Version 1.0.0
 ********************************/
public class LeetCode47 {

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private static void dfs(List<List<Integer>> res, List<Integer> curr, int[] nums, boolean[] flag) {

        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (flag[i] || i > 0 && nums[i] == nums[i - 1] && !flag[i - 1]) {
                continue;
            }
            curr.add(nums[i]);
            flag[i] = true;
            dfs(res, curr, nums, flag);
            curr.remove(curr.size() - 1);
            flag[i] = false;
        }
    }

    public static void main(String[] args) {
        LeetCode47 test = new LeetCode47();

        int[] nums = {1, 2, 1};
        System.out.println(test.permuteUnique(nums));
    }
}
