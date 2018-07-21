package com.ccq.springbootkafka.algorithm.segmentTree;

/********************************
 *** merge实现方法
 ***@Author chengchuanqiang
 ***@Date 2018/7/21 9:28
 ***@Version 1.0.0
 ********************************/
public interface Merger<E> {

    E merge(E a, E b);
}
