package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/14 9:55
 ***@Version 1.0.0
 ********************************/
public class LeetCode31 {

    public void nextPermutation(int[] nums) {

        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 0, nums, new boolean[nums.length]);
        int index = res.indexOf(numList);
        index = (index + 1) % res.size();

        for (int i = 0; i < nums.length; i++) {
            nums[i] = res.get(index).get(i);
        }

    }

    public static void main(String[] args) {
        LeetCode31 test = new LeetCode31();
//        test.getPermutation();

        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 1, 3};
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] nums4 = {6, 7, 5, 3, 5, 6, 2, 9, 1, 2, 7, 0, 9};

        test.nextPermutation(nums1);
        test.print(nums1);

        test.nextPermutation(nums2);
        test.print(nums2);

        test.nextPermutation(nums3);
        test.print(nums3);

        test.nextPermutation(nums4);
        test.print(nums4);

        test.getPermutation();

    }

    private void getPermutation() {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        int[] nums = {1, 2, 3};
        Arrays.sort(nums);
        boolean[] flag = new boolean[nums.length];
        dfs(res, curr, 0, nums, flag);

        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    private void dfs(List<List<Integer>> res, List<Integer> curr, int index, int[] nums, boolean[] flag) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !flag[i - 1] || flag[i]) {
                continue;
            }
            curr.add(nums[i]);
            flag[i] = true;
            dfs(res, curr, index + 1, nums, flag);
            curr.remove(curr.size() - 1);
            flag[i] = false;
        }
    }

    private void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
