package com.ccq.springbootkafka.generic;

import java.util.ArrayList;
import java.util.List;

/********************************
 *** 泛型方法示例
 ***@Author chengchuanqiang
 ***@Date 2018/10/19 14:32
 ***@Version 1.0.0
 ********************************/
public class GenericMethod {

    // 不带返回参数 泛型方法使用
    private <T> void test(T t) {
        System.out.println(t.getClass().getName());
    }

    // 带泛型返回参数 泛型方法使用
    private <T> T test1(T t) {
        System.out.println(t.getClass().getName());
        return t;
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        String s1 = "s";
        Integer s2 = 1;
        List<String> list = new ArrayList<>();
        genericMethod.test(s1);
        genericMethod.test(s2);
        genericMethod.test(list);

        System.out.println("=========================");

        genericMethod.test1(s1);
        genericMethod.test1(s2);
        genericMethod.test1(list);
    }
}