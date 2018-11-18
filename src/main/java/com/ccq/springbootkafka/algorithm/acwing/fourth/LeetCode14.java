package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.*;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/12 9:47
 ***@Version 1.0.0
 ********************************/
public class LeetCode14 {

    public String longestCommonPrefix(String[] strs) {
        StringBuilder sbf = new StringBuilder();
        List<String> list = Arrays.asList(strs);
        int min = list.stream().min(Comparator.comparingInt(String::length)).orElse("").length();
        if (min == 0) {
            return "";
        }
        boolean flag;
        for (int i = 0; i < min; i++) {
            flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != strs[j - 1].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sbf.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return sbf.toString();
    }

    public static void main(String[] args) {
        String[] s1 = {"flower", "flow", "flight"};
        String[] s2 = {"dog", "racecar", "car"};

        LeetCode14 test = new LeetCode14();
        System.out.println(test.longestCommonPrefix(s1));
        System.out.println(test.longestCommonPrefix(s2));
    }

}
