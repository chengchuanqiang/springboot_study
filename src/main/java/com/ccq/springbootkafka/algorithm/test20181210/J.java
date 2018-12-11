package com.ccq.springbootkafka.algorithm.test20181210;

import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/11 10:15
 ***@Version 1.0.0
 ********************************/
public class J {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        System.out.println(add(input.next(), input.next()));
        System.out.println(input.nextBigInteger().add(input.nextBigInteger()));
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
