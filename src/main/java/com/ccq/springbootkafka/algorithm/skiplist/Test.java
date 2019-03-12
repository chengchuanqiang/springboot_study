package com.ccq.springbootkafka.algorithm.skiplist;

import java.util.LinkedList;
import java.util.List;

/********************************
 *** 测试类（测试SkipList和LinkedList查询效率）
 ***@Author chengchuanqiang
 ***@Date 2019/3/12 15:10
 ***@Version 1.0.0
 ********************************/
public class Test {

    public static void main(String[] args) {
        int n = 100000;
        SkipList<String> skipList = new SkipList<>();
        for (int i = 0; i < n; i++) {
            skipList.put(i, i + "==");
        }
        long s, e;

        s = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            skipList.get(i);
        }
        e = System.currentTimeMillis();
        System.out.println("SkipList: " + (e - s) / 1000.0 + " s");

        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            linkedList.add(i);
        }
        s = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            linkedList.get(i);
        }
        e = System.currentTimeMillis();
        System.out.println("LinkedList: " + (e - s) / 1000.0 + " s");
    }

}
