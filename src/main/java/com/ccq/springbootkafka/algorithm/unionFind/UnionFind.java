package com.ccq.springbootkafka.algorithm.unionFind;

/********************************
 *** 定义并查集接口
 ***@Author chengchuanqiang
 ***@Date 2018/7/25 16:51
 ***@Version 1.0.0
 ********************************/
public interface UnionFind {
    /**
     * 查询x的根节点
     * @param x 参数下标
     * @return 根节点下标
     */
    int find(int x);

    /**
     * x,y是否在一个集合
     * @param x 参数x下标
     * @param y 参数y下标
     * @return 是否成功
     */
    boolean isConnect(int x, int y);

    /**
     * 连接x,y
     * @param x 参数x下标
     * @param y 参数y下标
     */
    void connect(int x, int y);

}
