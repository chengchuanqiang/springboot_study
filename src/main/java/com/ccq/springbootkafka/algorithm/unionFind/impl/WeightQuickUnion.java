package com.ccq.springbootkafka.algorithm.unionFind.impl;

import com.ccq.springbootkafka.algorithm.unionFind.UnionFind;

/********************************
 *** 记录集合元素个数
 ***@Author chengchuanqiang
 ***@Date 2018/7/25 18:28
 ***@Version 1.0.0
 ********************************/
public class WeightQuickUnion implements UnionFind {

    private int[] parent;
    private int size;
    private int[] weight; // 集合元素个数

    public WeightQuickUnion() {
    }

    public WeightQuickUnion(int n) {
        this.size = n;
        parent = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    @Override
    public int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    @Override
    public boolean isConnect(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        return rootX == rootY;
    }

    @Override
    public void connect(int x, int y) {

        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }
        if (weight[rootX] > weight[rootY]) {
            parent[rootY] = rootX;
            weight[rootX] += weight[rootY];
        } else {
            parent[rootX] = rootY;
            weight[rootY] += weight[rootX];
        }
    }
}
