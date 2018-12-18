package com.ccq.springbootkafka.algorithm.test20181113;

import java.util.Scanner;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/11/13 23:03
 */
public class B {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();

            if (n % 3 == 0) {
                System.out.println("Hershel Layton will lose.");
            } else {
                System.out.println("Hershel Layton will win.");
            }
        }
    }
}
