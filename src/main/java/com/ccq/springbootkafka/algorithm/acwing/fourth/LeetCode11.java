package com.ccq.springbootkafka.algorithm.acwing.fourth;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/11/18 18:06
 */
public class LeetCode11 {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        LeetCode11 test = new LeetCode11();
        System.out.println(test.maxArea(height));
    }

    public int maxArea(int[] height) {
        int max = 0;
        int curr = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                curr = (j - i) * Math.min(height[i], height[j]);
                if (curr > max) {
                    max = curr;
                }
            }
        }
        return max;
    }
}
