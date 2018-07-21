package com.ccq.springbootkafka.algorithm.segmentTree;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/21 9:34
 ***@Version 1.0.0
 ********************************/
public class Main {
    public static void main(String[] args) {
        Integer[] nums = {1, 3, 2, 2, 4, 5, 6, 1};

        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);

        System.out.println(segmentTree.toString());
        System.out.println(segmentTree.query(0, 0));
        segmentTree.update(0, 100);
        System.out.println(segmentTree.query(0, 7));
        segmentTree.update(1, 100);
        System.out.println(segmentTree.query(0, 0));
        System.out.println(segmentTree.query(0, 7));

    }
}
