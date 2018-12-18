package com.ccq.springbootkafka.algorithm.acwing.fourth;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/11/18 16:25
 */
public class LeetCode33 {

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        LeetCode33 test = new LeetCode33();
        System.out.println(test.search(nums1, 0));
        System.out.println(test.search(nums1, 3));

        int[] nums2 = {5, 1, 3};
        System.out.println(test.search(nums2, 3));

        int[] nums3 = {4, 5, 6, 7, 8, 1, 2, 3};
        System.out.println(test.search(nums3, 8));

        int[] nums4 = {3, 5, 1};
        System.out.println(test.search(nums4, 3));

        int[] nums5 = {3, 1};
        System.out.println(test.search(nums5, 1));
    }

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                if (nums[l] < nums[r]) {
                    // 递增的
                    r = mid - 1;
                } else {
                    if (nums[mid] <= nums[l] && nums[mid] >= nums[r]) {
                        // 递减的
                        l = mid + 1;
                    } else {
                        if (nums[mid] > nums[l]) {
                            if (target >= nums[l]) {
                                r = mid - 1;
                            } else {
                                l = mid + 1;
                            }
                        } else {
                            if (target > nums[r]) {
                                l = mid + 1;
                            } else {
                                r = mid - 1;
                            }
                        }
                    }
                }
            } else {
                if (nums[l] < nums[r]) {
                    // 递增的
                    l = mid + 1;
                } else {
                    if (nums[mid] <= nums[l] && nums[mid] >= nums[r]) {
                        // 递减的
                        r = mid - 1;
                    } else {
                        if (nums[mid] > nums[l]) {
                            if (target >= nums[l]) {
                                l = mid + 1;
                            } else {
                                r = mid - 1;
                            }
                        } else {
                            if (target > nums[r]) {
                                r = mid - 1;
                            } else {
                                l = mid + 1;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
