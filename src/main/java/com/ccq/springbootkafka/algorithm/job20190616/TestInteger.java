package com.ccq.springbootkafka.algorithm.job20190616;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/6/26 8:47
 */
public class TestInteger {

    public static void cacl(Integer a) {
        a = new Integer(2);
    }

    public static void main(String[] args) {
        Integer b = new Integer(1);

        Integer a = b;

        cacl(b);

        System.out.println(a.intValue());
        System.out.println(b.intValue());
        System.out.println(a == b);
    }

}
