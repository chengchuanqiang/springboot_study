package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Arrays;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/20 18:06
 ***@Version 1.0.0
 ********************************/
public class LeetCode88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] nums = Arrays.copyOf(nums1, m);
        while (i < m && j < n) {
            if (nums[i] < nums2[j]) {
                nums1[k++] = nums[i++];
            } else if (nums[i] > nums2[j]) {
                nums1[k++] = nums2[j++];
            } else {
                nums1[k++] = nums[i++];
                nums1[k++] = nums2[j++];
            }
        }

        while (i < m) {
            nums1[k++] = nums[i++];
        }
        while (j < n) {
            nums1[k++] = nums2[j++];
        }
    }

    public static void main(String[] args) {
        LeetCode88 test = new LeetCode88();

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};

        test.merge(nums1, 3, nums2, 3);

        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();
    }
}
