package com.ccq.springbootkafka.test;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/4/22 0:17
 */
public class Test {

    public static void main(String[] args) {
        Integer a = 59;
        int b = 59;
        Integer c = Integer.valueOf(59);
        Integer d = new Integer(59);

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(c == d);
        System.out.println(b == d);
    }

}
