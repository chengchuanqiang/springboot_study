package com.ccq.springbootkafka.algorithm.henanacm12;

import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/5/23 14:42
 ***@Version 1.0.0
 ********************************/
public class A {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while ((t--) > 0) {
            String mode = input.next();
            String s = input.next();

            String modeReverse = new StringBuilder(mode).reverse().toString();
            int res = 0;
            String temp = s;
            while (temp.contains(mode)) {
                int index = temp.indexOf(mode) + 1;
                temp = temp.substring(index);
                res++;
            }
            if (!mode.equals(modeReverse)) {
                temp = s;
                while (temp.contains(modeReverse)) {
                    int index = temp.indexOf(modeReverse) + 1;
                    temp = temp.substring(index);
                    res++;
                }
            }
            System.out.println(res);
        }
    }
}
