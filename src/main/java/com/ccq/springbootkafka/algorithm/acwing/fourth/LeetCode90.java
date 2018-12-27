package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************
 *** 有重复查询子集
 ***@Author chengchuanqiang
 ***@Date 2018/12/26 19:52
 ***@Version 1.0.0
 ********************************/
public class LeetCode90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> curr, int[] nums, int index) {
        res.add(new ArrayList<>(curr));

        if (curr.size() == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            curr.add(nums[i]);
            dfs(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode90 test = new LeetCode90();

        int[] nums = {1, 2, 2, 4, 4};
        System.out.println(test.subsetsWithDup(nums));
    }
}

