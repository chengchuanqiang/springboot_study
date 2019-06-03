package com.ccq.springbootkafka.quartz;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/5/30 18:44
 ***@Version 1.0.0
 ********************************/
public class Test {

    public static void main(String[] args) {
        System.out.println(solve("abbbce"));
        System.out.println(solve("aabbcc"));
        System.out.println(solve("aabce"));
        System.out.println(solve("aabbcce"));
    }

    public static char solve(String str) {
        if (null == str || str.length() == 0) {
            return ' ';
        }
        char[] chars = str.toCharArray();
        if (chars.length == 1) {
            return chars[0];
        }
        if (chars.length == 2) {
            if (chars[0] == chars[1]) {
                return ' ';
            } else {
                return chars[0];
            }
        }

        for (int i = 2; i < chars.length; i++) {
            if (i == chars.length - 1 && chars[i] != chars[i - 1]) {
                return chars[i];
            }
            if (chars[i] != chars[i - 1] && chars[i] != chars[i + 1]) {
                return chars[i];
            }
        }
        return ' ';
    }
}
