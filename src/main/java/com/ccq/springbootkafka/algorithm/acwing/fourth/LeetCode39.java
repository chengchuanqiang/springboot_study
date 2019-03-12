package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/27 18:05
 ***@Version 1.0.0
 ********************************/
public class LeetCode39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == candidates || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
//        dfs(res, new ArrayList<>(), target, candidates);
        dfs2(res, new ArrayList<>(), target, candidates,0);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> curr, int target, int[] nums) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                continue;
            }
            if (curr.size() > 0 && nums[i] < curr.get(curr.size() - 1)) {
                continue;
            }
            curr.add(nums[i]);
            dfs(res, curr, target - nums[i], nums);
            curr.remove(curr.size() - 1);
        }
    }


    private void dfs2(List<List<Integer>> res, ArrayList<Integer> curr, int target, int[] nums, int index) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        } else {
            for (int i = index; i < nums.length; i++) {
                curr.add(nums[i]);
                dfs2(res, curr, target - nums[i], nums, i);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode39 test = new LeetCode39();

        int[] nums = {2, 3, 5};
        System.out.println(test.combinationSum(nums, 8));

        nums = new int[]{2, 3, 6, 7};
        System.out.println(test.combinationSum(nums, 7));
    }
}
