package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/********************************
 *** 不重复数字的全排列
 ***@Author chengchuanqiang
 ***@Date 2018/12/19 19:32
 ***@Version 1.0.0
 ********************************/
public class LeetCode46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return res;
        }
        dfs(res, new ArrayList<>(), nums);
        return res;
    }

    private static void dfs(List<List<Integer>> res, List<Integer> curr, int[] nums) {

        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(curr.contains(nums[i])){
                continue;
            }
            curr.add(nums[i]);
            dfs(res, curr, nums);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode46 test = new LeetCode46();

        int[] nums = {1, 2, 3};
        System.out.println(test.permute(nums));
    }
}
