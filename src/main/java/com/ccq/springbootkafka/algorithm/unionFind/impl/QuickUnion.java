package com.ccq.springbootkafka.algorithm.unionFind.impl;

import com.ccq.springbootkafka.algorithm.unionFind.UnionFind;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/25 18:01
 ***@Version 1.0.0
 ********************************/
public class QuickUnion implements UnionFind {

    private int[] parent;
    private int size;

    public QuickUnion() {
    }

    public QuickUnion(int n) {
        this.size = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int find(int x) {
        while (x != parent[x]){
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
        if(rootX == rootY){
            return;
        }
        parent[rootY] = rootX;
    }
}
