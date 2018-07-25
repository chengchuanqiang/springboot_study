package com.ccq.springbootkafka.algorithm.unionFind.impl;

import com.ccq.springbootkafka.algorithm.unionFind.UnionFind;

/********************************
 *** 记录集合高度
 ***@Author chengchuanqiang
 ***@Date 2018/7/25 18:28
 ***@Version 1.0.0
 ********************************/
public class HeightQuickUnion implements UnionFind {

    private int[] parent;
    private int size;
    private int[] height; // 集合元素的高度

    public HeightQuickUnion() {
    }

    public HeightQuickUnion(int n) {
        this.size = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
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
        if (height[rootX] > height[rootY]) {
            parent[rootY] = rootX;
        } else if (height[rootX] > height[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootX] = rootY;
            height[rootY]++;
        }
    }
}
