package com.ccq.springbootkafka.algorithm.acwing.fourth;

/**
 * @Description: https://leetcode.com/problems/palindrome-partitioning-ii/
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 7:53
 */
public class LeetCode132 {

    public static void main(String[] args) {
        LeetCode132 test = new LeetCode132();
        System.out.println(test.minCut("aa"));
    }

    /**
     * dp[i]  前i个字符可以分割回文串的个数
     * dp[i] = min(dp[j] + 1 && s[j,i] is Palindrome)
     *
     * @param s s
     * @return result
     */
    public int minCut(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        char[] chars = s.toCharArray();
        boolean[][] isPalindrome = new boolean[len][len];
        for (int k = 0; k < len; k++) {
            // odd-length
            int i = k;
            int j = k;
            while (i >= 0 && j < len && chars[i] == chars[j]) {
                isPalindrome[i][j] = true;
                i--;
                j++;
            }
            // even-length
            i = k;
            j = k + 1;
            while (i >= 0 && j < len && chars[i] == chars[j]) {
                isPalindrome[i][j] = true;
                i--;
                j++;
            }
        }


        int[] dp = new int[len + 1];
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[i - j] + 1);
                }
            }
        }
        return dp[len] - 1;
    }

}
