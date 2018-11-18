package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Map;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/11/18 18:17
 */
public class LeetCode41 {

    public static void main(String[] args) {
        LeetCode41 test = new LeetCode41();

        int[] nums1 = {1, 2, 0};
        System.out.println(test.firstMissingPositive(nums1));

        int[] nums2 = {3, 4, -1, 1};
        System.out.println(test.firstMissingPositive(nums2));

        int[] nums3 = {7, 8, 9, 11, 12};
        System.out.println(test.firstMissingPositive(nums3));
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] - 1 < n) {
                res[nums[i] - 1] = nums[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (res[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
