package com.ccq.springbootkafka.generic;

import java.util.ArrayList;
import java.util.List;

/********************************
 *** 泛型类
 ***@Author chengchuanqiang
 ***@Date 2018/10/19 14:52
 ***@Version 1.0.0
 ********************************/
public class GenericTest {

    public static void main(String[] args) {
        GenericTest genericTest = new GenericTest();
        genericTest.test();
    }

    private void test() {
        Test<String> test1 = new Test<>();

        Test<Integer> test2 = new Test<>();
        Test<List<String>> test3 = new Test<>();

        test1.method1("sss");
        test2.method1(12);
        test3.method1(new ArrayList<>());

        System.out.println("=================");

        test1.method2("sss");
        test2.method2(12);
        test3.method2(new ArrayList<>());
    }

    /**
     * 泛型类
     *
     * @param <T>
     */
    class Test<T> {

        void method1(T t) {
            System.out.println(t.getClass().getName());
        }

        T method2(T t) {
            System.out.println(t.getClass().getName());
            return t;
        }
    }
}