package com.ccq.springbootkafka.algorithm.jisuanke.y2018_4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * @Description: https://nanti.jisuanke.com/t/A1709
 * @Author: ChengChuanQiang
 * @Date: 2019/5/19 14:36
 */
public class A {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();

        while ((T--) > 0) {
            String[] s = input.next().split("\\.");
            if (s.length == 1) {
                System.out.println(s);
            } else {
                char[] chars = s[1].toCharArray();
                // 是否进位
                boolean flag = false;
                for (int i = chars.length - 1; i >= 0; i--) {
                    int temp = chars[i] - '0';
                    if (flag) {
                        temp++;
                    }
                    flag = temp >= 5;
                }
                if (flag) {

                    BigInteger res = new BigInteger(s[0]);
                    res = res.add(new BigInteger("1"));
                    System.out.println(res);
                } else {
                    System.out.println(s[0]);
                }

            }
        }
    }


}
