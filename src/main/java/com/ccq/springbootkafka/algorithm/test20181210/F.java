package com.ccq.springbootkafka.algorithm.test20181210;

import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/10 14:24
 ***@Version 1.0.0
 ********************************/
public class F {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        String[] sum = new String[T];

        for (int i = 0; i < T; i++) {
            sum[i] = input.next();
        }
        for (int i = 1; i < T; i++) {
            sum[i] = add(sum[i], sum[i - 1]);
        }

        int n = input.nextInt();
        while ((n--) > 0) {
            System.out.println(sum[input.nextInt() - 1]);
        }
        input.close();
    }

    private static String add(String s1, String s2) {
        int t = 0;
        int sum = 0;
        int i, j;
        StringBuilder sbf = new StringBuilder();
        for (i = s1.length() - 1, j = s2.length() - 1; i >= 0 && j >= 0; i--, j--) {
            sum = s1.charAt(i) - '0' + s2.charAt(j) - '0' + t;
            sbf.append(sum % 10);
            t = sum / 10;
        }

        if (i >= 0) {
            for (; i >= 0; i--) {
                sum = s1.charAt(i) - '0' + t;
                sbf.append(sum % 10);
                t = sum / 10;
            }
        }
        if (j >= 0) {
            for (; j >= 0; j--) {
                sum = s2.charAt(j) - '0' + t;
                sbf.append(sum % 10);
                t = sum / 10;
            }
        }
        if (t > 0) {
            sbf.append(t);
        }
        return sbf.reverse().toString();
    }
}
