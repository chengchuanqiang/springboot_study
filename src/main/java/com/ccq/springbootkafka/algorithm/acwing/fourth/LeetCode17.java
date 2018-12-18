package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/18 9:39
 ***@Version 1.0.0
 ********************************/
public class LeetCode17 {

    private static String[] nums = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (null == digits || digits.length() == 0) {
            return res;
        }

        dfs(res, new StringBuilder(), 0, digits.toCharArray());
        return res;
    }

    private void dfs(List<String> res, StringBuilder sbf, int index, char[] digits) {
        if (sbf.length() == digits.length) {
            res.add(new String(sbf));
            return;
        }

        for (int i = index; i < digits.length; i++) {
            char[] chars = nums[digits[i] - '0'].toCharArray();
            for (char cc : chars) {
                sbf.append(cc);
                dfs(res, sbf, i + 1, digits);
                sbf.deleteCharAt(sbf.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode17 test = new LeetCode17();

        System.out.println(test.letterCombinations("23456789"));
    }
}
