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

        }
    }
}
