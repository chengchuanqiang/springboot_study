package com.ccq.springbootkafka.algorithm.jishao;

import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/4/12 19:01
 ***@Version 1.0.0
 ********************************/
public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(",");
        int categoryCount = Integer.valueOf(line[0]);
        int totalVolume = Integer.valueOf(line[1]);
        int totalWeight = Integer.valueOf(line[2]);

        int[] volume = new int[50];
        int[] weight = new int[50];
        int[] stock = new int[50];
        int[] price = new int[50];
        int[] itemType = new int[50];

        for (int i = 1; i <= categoryCount; i++) {
            line = in.nextLine().split(",");
            volume[i] = Integer.valueOf(line[0]);
            weight[i] = Integer.valueOf(line[1]);
            stock[i] = Integer.valueOf(line[2]);
            price[i] = Integer.valueOf(line[3]);
            itemType[i] = Integer.valueOf(line[4]);
        }
        in.close();
        System.out.println(totalPrice(categoryCount, totalVolume, totalWeight, volume, weight, stock, price, itemType));
    }

    private static int totalPrice(int categoryCount, int totalVolume, int totalWeight, int[] volume, int[] weight, int[] stock, int[] price, int[] itemType) {

        int categoryCount1 = 0;
        int[] volume1 = new int[50];
        int[] weight1 = new int[50];
        int[] stock1 = new int[50];
        int[] price1 = new int[50];

        int categoryCount2 = 0;
        int[] volume2 = new int[50];
        int[] weight2 = new int[50];
        int[] stock2 = new int[50];
        int[] price2 = new int[50];

        for (int i = 1; i <= categoryCount; i++) {
            if (itemType[i] == 1) {
                categoryCount1++;
                volume1[categoryCount1] = volume[i];
                weight1[categoryCount1] = weight[i];
                stock1[categoryCount1] = stock[i];
                price1[categoryCount1] = price[i];
            } else if (itemType[i] == 3) {
                categoryCount2++;
                volume2[categoryCount2] = volume[i];
                weight2[categoryCount2] = weight[i];
                stock2[categoryCount2] = stock[i];
                price2[categoryCount2] = price[i];
            } else {
                categoryCount1++;
                volume1[categoryCount1] = volume[i];
                weight1[categoryCount1] = weight[i];
                stock1[categoryCount1] = stock[i];
                price1[categoryCount1] = price[i];

                categoryCount2++;
                volume2[categoryCount2] = volume[i];
                weight2[categoryCount2] = weight[i];
                stock2[categoryCount2] = stock[i];
                price2[categoryCount2] = price[i];
            }
        }

        return Math.max(solve(categoryCount1, totalVolume, totalWeight, volume1, weight1, stock1, price1), solve(categoryCount2, totalVolume, totalWeight, volume2, weight2, stock2, price2));

    }

    private static int solve(int categoryCount, int totalVolume, int totalWeight, int[] volume, int[] weight, int[] stock, int[] price) {
        if (categoryCount <= 0) {
            return 0;
        }
        // dp[i][j][k] 表示可选前i中物品，总体积不超过j,重量不超过k的值
        int[][][] dp = new int[categoryCount + 1][totalVolume + 1][totalWeight + 1];

        for (int i = 1; i <= categoryCount; i++) {
            for (int j = 0; j <= totalVolume; j++) {
                for (int k = 0; k <= totalWeight; k++) {
                    int max = dp[i - 1][j][k];

                    if (j >= volume[i] && k >= weight[i]) {
                        for (int s = 0; s <= min(j / volume[i], k / weight[i], stock[i]); s++) {
                            max = Math.max(max, dp[i - 1][j - s * volume[i]][k - s * weight[i]] + s * price[i]);
                        }
                    }
                    dp[i][j][k] = max;
                }
            }
        }

        return dp[categoryCount][totalVolume][totalWeight];
    }

    private static int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }
}
