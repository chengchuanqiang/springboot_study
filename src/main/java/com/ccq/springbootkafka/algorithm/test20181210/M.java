package com.ccq.springbootkafka.algorithm.test20181210;

import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/11 10:26
 ***@Version 1.0.0
 ********************************/
public class M {

    private static String[] str = {"2(0)", "2", "2(2)", "2(2+2(0))", "2(2(2))", "2(2(2)+2(0))", "2(2(2)+2)", "2(2(2)+2+2(0))", "2(2(2+2(0)))", "2(2(2+2(0))+2(0))", "2(2(2+2(0))+2)", "2(2(2+2(0))+2+2(0))", "2(2(2+2(0))+2(2))", "2(2(2+2(0))+2(2)+2(0))", "2(2(2+2(0))+2(2)+2)", "2(2(2+2(0))+2(2)+2+2(0))"};

    //2(2(2+2(0))+2)  +  2(2(2+2(0)))  +   2(2(2)+2(0))   +2+   2(0)
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[] bStr = Integer.toBinaryString(input.nextInt()).toCharArray();
//        System.out.println(bStr);
        StringBuilder sbf = new StringBuilder();
        for (int i = 0; i < bStr.length; i++) {
            if (bStr[i] == '1') {
                sbf.append(str[bStr.length - i - 1]).append("+");
            }
        }
        System.out.println(sbf.substring(0, sbf.length() - 1));
    }
}
