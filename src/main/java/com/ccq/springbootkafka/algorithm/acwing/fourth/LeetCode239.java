package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.*;

/********************************
 *** 滑动窗口 单调队列
 ***@Author chengchuanqiang
 ***@Date 2019/5/8 10:00
 ***@Version 1.0.0
 ********************************/
public class LeetCode239 {

    public static void main(String[] args) {
        LeetCode239 test = new LeetCode239();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(test.maxSlidingWindow(nums, 3)));
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }

        return res;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int[] res = new int[nums.length - k + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {

            if (!queue.isEmpty() && i - queue.getFirst() >= k) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && nums[queue.getLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (i >= k - 1) {
                res[index++] = nums[queue.getFirst()];
            }
        }
        return res;
    }

}