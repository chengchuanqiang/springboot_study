package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Stack;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/12 19:51
 ***@Version 1.0.0
 ********************************/
public class LeetCode20 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode20 test = new LeetCode20();

        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)";
        String s5 = "}";

        System.out.println(test.isValid(s1));
        System.out.println(test.isValid(s2));
        System.out.println(test.isValid(s3));
        System.out.println(test.isValid(s4));
        System.out.println(test.isValid(s5));

    }

}
