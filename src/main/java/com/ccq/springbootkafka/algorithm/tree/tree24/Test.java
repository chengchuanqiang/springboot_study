package com.ccq.springbootkafka.algorithm.tree.tree24;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/12 14:02
 ***@Version 1.0.0
 ********************************/
public class Test {
    public static void main(String[] args) {

        Tree24<Integer> test = new Tree24<>();

        test.insert(1);
        test.insert(2);
        test.insert(3);
        test.insert(4);
        test.insert(5);
        test.insert(6);
        test.insert(7);
        test.insert(8);

        System.out.println(test);
    }
}
