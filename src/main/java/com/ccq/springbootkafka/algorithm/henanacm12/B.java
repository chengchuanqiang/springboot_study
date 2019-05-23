package com.ccq.springbootkafka.algorithm.henanacm12;

import java.util.Scanner;

/********************************
 *** 河南acm省赛B题
 ***@Author chengchuanqiang
 ***@Date 2019/5/7 10:59
 ***@Version 1.0.0
 ********************************/
public class B {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();
            int d = input.nextInt();

            String s = input.next();
            String t = input.next();
            int[][] f = new int[s.length() + 1][t.length() + 1];
            for (int i = 1; i <= s.length(); i++) {
                f[i][0] = -d * i;
            }
            for (int j = 1; j <= t.length(); j++) {
                f[0][j] = -c * j;
            }

            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= t.length(); j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        f[i][j] = f[i - 1][j - 1] + a;
                    } else {
                        f[i][j] = Math.max(Math.max(f[i - 1][j] - d, f[i][j - 1] - c), f[i - 1][j - 1] - b);
                    }
                }
            }

            System.out.println(f[s.length()][t.length()]);
        }
    }
}
