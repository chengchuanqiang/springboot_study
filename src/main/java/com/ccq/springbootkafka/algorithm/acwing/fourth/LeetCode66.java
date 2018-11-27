package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Arrays;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/27 17:51
 ***@Version 1.0.0
 ********************************/
public class LeetCode66 {

    public int[] plusOne(int[] digits) {
        int one = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + one;

            if (digits[i] == 10) {
                digits[i] = 0;
                one = 1;
            } else {
                one = 0;
                break;
            }
        }
        if (one == 1) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                res[i + 1] = digits[i];
            }
            return res;
        }
        return digits;
    }

    public static void main(String[] args) {
        LeetCode66 test = new LeetCode66();

        int[] dig1 = {1, 2, 3};
        System.out.println(Arrays.toString(test.plusOne(dig1)));

        int[] dig2 = {9, 9, 9};
        System.out.println(Arrays.toString(test.plusOne(dig2)));
    }
}
