package com.ccq.springbootkafka.algorithm.dp;

import java.util.Scanner;

/********************************
 *** 对抗赛  http://www.ncwu.club/problem.php?id=1783
 ***@Author chengchuanqiang
 ***@Date 2019/4/25 11:22
 ***@Version 1.0.0
 ********************************/
public class DualMeet {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] price = new int[n + 1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            price[i] = input.nextInt();
            sum += price[i];
        }
        if (sum % 2 != 0) {
            System.out.println(0);
        } else {
            sum = sum / 2;
            int[][] dp = new int[n + 1][sum + 1];
            int[][] res = new int[n + 1][sum + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= sum; j++) {
                    dp[i][j] = Integer.MIN_VALUE;
                }
            }
            dp[0][0] = 0;
            res[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= sum; j++) {
                    if (j < price[i]) {
                        dp[i][j] = dp[i - 1][j];
                        res[i][j] = res[i - 1][j];
                    } else {
                        if (dp[i - 1][j] > dp[i - 1][j - price[i]] + price[i]) {
                            dp[i][j] = dp[i - 1][j];
                            res[i][j] = res[i - 1][j];
                        } else {
                            dp[i][j] = dp[i - 1][j - price[i]] + price[i];
                            res[i][j] = res[i - 1][j] + res[i - 1][j - price[i]];
                        }
                    }
                }
            }

            System.out.println(res[n][sum] / 2);
        }
    }
}
