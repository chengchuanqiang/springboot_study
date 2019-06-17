package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 *** https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 ***@Author chengchuanqiang
 ***@Date 2019/6/17 17:35
 ***@Version 1.0.0
 ********************************/
public class LeetCode154 {

    public static void main(String[] args) {
        LeetCode154 test = new LeetCode154();

        int[] nums = {1, 3, 3};

        System.out.println(test.findMin(nums));
    }

    int min = Integer.MAX_VALUE;

    public int findMin(int[] nums) {
        findMin(0, nums.length - 1, nums);
        return min;

    }

    private void findMin(int l, int r, int[] nums) {

        if (l > r) {
            return;
        }
        int mid = (l + r) >> 1;
        if (nums[mid] < min) {
            min = nums[mid];
        }

        if (nums[mid] > nums[l]) {
            if (nums[mid] < nums[r]) {
                if (nums[l] < min) {
                    min = nums[l];
                }
            } else if (nums[mid] > nums[r]) {
                findMin(mid + 1, r, nums);
            } else {
                findMin(l, mid - 1, nums);
            }
        } else if (nums[mid] < nums[l]) {
            if (nums[mid] > nums[r]) {
                if (nums[r] < min) {
                    min = nums[r];
                }
            } else if (nums[mid] <= nums[r]) {
                findMin(l, mid - 1, nums);
            }
        } else {
            findMin(mid + 1, r, nums);
            findMin(l, mid - 1, nums);
        }

    }


}
