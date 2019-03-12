package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************
 *** 无重复查询子集
 ***@Author chengchuanqiang
 ***@Date 2018/12/26 19:52
 ***@Version 1.0.0
 ********************************/
public class LeetCode78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs2(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> curr, int[] nums, int index) {
        res.add(new ArrayList<>(curr));

        if (curr.size() == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            curr.add(nums[i]);
            dfs(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    private void dfs2(List<List<Integer>> res, ArrayList<Integer> curr, int[] nums, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[index]); // 选
        dfs2(res, curr, nums, index + 1);
        curr.remove(curr.size() - 1); // 不选
        dfs2(res, curr, nums, index + 1);
    }

    public static void main(String[] args) {
        LeetCode78 test = new LeetCode78();

        int[] nums = {1, 2, 3};
        System.out.println(test.subsets(nums));
    }
}

