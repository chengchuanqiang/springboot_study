package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/20 13:45
 ***@Version 1.0.0
 ********************************/
public class LeetCode125 {

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replace(" ", "");
        if (s.isEmpty()) {
            return true;
        }
        int i = -1;
        int j = s.length();
        char l, r;
        while (i < j) {
            l = s.charAt(++i);
            r = s.charAt(--j);
            while (!isOk(l) && i <= j && i + 1 < s.length() - 1) {
                l = s.charAt(++i);
            }

            while (!isOk(r) && i <= j && j - 1 > 0) {
                r = s.charAt(--j);
            }
            if (l != r && isOk(l) && isOk(r)) {
                return false;
            }
        }
        return true;

    }

    public boolean isOk(char c) {
        if (c >= '0' && c <= '9' || c >= 'a' && c <= 'z') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode125 test = new LeetCode125();

        System.out.println(test.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(test.isPalindrome(""));
        System.out.println(test.isPalindrome("race a car"));
        System.out.println(test.isPalindrome("a."));
        System.out.println(test.isPalindrome("."));
        System.out.println(test.isPalindrome(".,"));
    }
}
