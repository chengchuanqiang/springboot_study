package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 *** https://leetcode.com/problems/decode-ways/description/
 ***@Author chengchuanqiang
 ***@Date 2019/5/14 9:59
 ***@Version 1.0.0
 ********************************/
public class LeetCode91 {

    public static void main(String[] args) {
        LeetCode91 test = new LeetCode91();

        System.out.println(test.numDecodings("226"));
    }

    /**
     * dp[i] 前i-1个字符分割方案个数
     * dp[i] = dp[i-1] (s[i-1] is char) + dp[i-2] (s[i-2][i-1] is char)
     *
     * @param s 字符串
     * @return 分割方案个数
     */
    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[0] = 1;
        for (int i = 1; i <= chars.length; i++) {
            dp[i] = 0;
            int temp = chars[i - 1] - '0';
            if (temp >= 1 && temp <= 9) {
                dp[i] += dp[i - 1];
            }
            if (i > 1) {
                temp = (chars[i - 2] - '0') * 10 + (chars[i - 1] - '0');
                if (temp >= 10 && temp <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[chars.length];
    }
}
