package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Arrays;

/********************************
 *** https://leetcode.com/problems/coin-change/description/
 ***@Author chengchuanqiang
 ***@Date 2019/5/13 9:39
 ***@Version 1.0.0
 ********************************/
public class LeetCode322 {

    public static void main(String[] args) {
        LeetCode322 test = new LeetCode322();
        int[] coins = {1, 2, 5};
        System.out.println(test.coinChange(coins, 11));
    }

    public int coinChange1(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        int MAX = 100000;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[amount] == MAX ? -1 : dp[amount];
    }
}
