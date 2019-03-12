package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/28 17:56
 ***@Version 1.0.0
 ********************************/
public class LeetCode40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == candidates || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        dfs(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> curr, int[] nums, int target, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (target < nums[i]) {
                continue;
            }
            // 保证重复元素的顺序
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            curr.add(nums[i]);
            dfs(res, curr, nums, target - nums[i], i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode40 test = new LeetCode40();

        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(test.combinationSum2(candidates, 8));
    }
}
