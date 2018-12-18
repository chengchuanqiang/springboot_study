package com.ccq.springbootkafka.algorithm.test20181210;

import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/10 17:54
 ***@Version 1.0.0
 ********************************/
public class H {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int x = input.nextInt();
        int y = input.nextInt();
        int z = input.nextInt();
        int res = 0;
        for (int i = 10; i <= 100; i++) {
            if (i % 3 == x && i % 5 == y && i % 7 == z) {
                res = i;
            }
        }
        if (res == 0) {
            System.out.println("no answer");
        } else {
            System.out.println(res);
        }

        input.close();
    }
}
