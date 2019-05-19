package com.ccq.springbootkafka.algorithm.yjx;

import java.util.Scanner;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/3/16 10:16
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] money = {1, 4, 16, 64};
        while (input.hasNext()) {
            int n = 1024 - input.nextInt();
            int m = money.length;

            int[][] dp = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= n; i++) {
                dp[0][i] = Integer.MAX_VALUE;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (i < money[j - 1]) {
                        dp[j][i] = dp[j - 1][i];
                    } else {
                        dp[j][i] = Math.min(dp[j - 1][i], dp[j][i - money[j - 1]] + 1);
                    }
                }
            }
            System.out.println(dp[m][n]);

        }
        input.close();
    }
}
