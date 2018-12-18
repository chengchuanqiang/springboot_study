package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/12/2 10:48
 */
public class LeetCode228 {

    public static void main(String[] args) {
        LeetCode228 test = new LeetCode228();

        int[] nums1 = {0, 1, 2, 4, 5, 7};
        System.out.println(test.summaryRanges(nums1));

        int[] nums2 = {0, 2, 3, 4, 6, 8, 9};
        System.out.println(test.summaryRanges(nums2));
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            res.add(nums[0] + "");
            return res;
        }
        int len = nums.length;
        int min = nums[0];
        int curr = nums[0];
        for (int i = 1; i < len; i++) {
            if (curr + 1 == nums[i]) {
                curr = nums[i];
            } else {
                if (min == curr) {
                    res.add(min + "");
                } else {
                    res.add(min + "->" + curr);
                }
                min = nums[i];
                curr = nums[i];
            }
        }
        if (curr == min) {
            res.add(min + "");
        } else {
            res.add(min + "->" + curr);
        }
        return res;

    }
}
