package com.ccq.springbootkafka.algorithm.lanqiaobei01;

import java.util.Scanner;

/********************************
 *** FJ的字符串
 *** http://www.ncwu.club/problem.php?id=4499
 ***@Author chengchuanqiang
 ***@Date 2019/3/10 10:36
 ***@Version 1.0.0
 ********************************/
public class FJString {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
//            String res = "";
//            dfs(n, 0, res);
            System.out.println(dfs(n));
        }
    }

    private static void dfs(int n, int i, String res) {
        if (i == n) {
            System.out.println(res);
            return;
        }
        if (i == 0) {
            res = "A";
        } else {
            res = res + (char) ('A' + i) + res;
        }
        dfs(n, i + 1, res);
    }

    private static String dfs(int n) {
        if (n == 1) {
            return "A";
        } else {
            return dfs(n - 1) + (char) ('A' + (n - 1)) + dfs(n - 1);
        }
    }
}
