package com.ccq.springbootkafka.algorithm.lintcode;

import org.springframework.util.CollectionUtils;

import java.util.Collections;

/********************************
 *** https://www.lintcode.com/problem/longest-continuous-increasing-subsequence/description
 ***@Author chengchuanqiang
 ***@Date 2019/5/15 18:07
 ***@Version 1.0.0
 ********************************/
public class LintCode397 {

    public static void main(String[] args) {
        LintCode397 test = new LintCode397();
        int[] A = {5, 4, 2, 1, 3};
        System.out.println(test.longestIncreasingContinuousSubsequence(A));
    }

    /**
     * @param A: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (null == A || A.length == 0) {
            return 0;
        }
        int res = solve(A);
        int i = 0;
        int j = A.length - 1;
        while (i < j) {
            int temp = A[i];
            A[i++] = A[j];
            A[j--] = temp;
        }
        return Math.max(res, solve(A));

    }

    private int solve(int[] A) {
        int[] dp = new int[A.length];
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            dp[i] = 1;
            if (i > 0 && A[i] > A[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            if (dp[i] > res) {
                res = dp[i];
            }
        }
        return res;
    }

}
