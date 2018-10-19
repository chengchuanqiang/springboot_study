package com.ccq.springbootkafka.generic;

import java.util.ArrayList;
import java.util.List;

/********************************
 *** 泛型方法
 ***@Author chengchuanqiang
 ***@Date 2018/10/19 14:32
 ***@Version 1.0.0
 ********************************/
public class GenericMethod {

    // 泛型方法使用
    private <T> void test(T t) {
        System.out.println(t.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        String s1 = "s";
        Integer s2 = 1;
        List<String> list = new ArrayList<>();
        genericMethod.test(s1);
        genericMethod.test(s2);
        genericMethod.test(list);
    }

}
