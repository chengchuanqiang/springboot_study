package com.ccq.springbootkafka.algorithm.unionFind;

/********************************
 *** 使用快速查询实现并查集 查询O(1) 关联O(N)
 ***@Author chengchuanqiang
 ***@Date 2018/7/25 16:56
 ***@Version 1.0.0
 ********************************/
public class QuickFind implements UnionFind {

    private int[] parent;
    private int size;

    private QuickFind() {
    }

    public QuickFind(int n) {
        this.size = n;
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int find(int x) {
        return parent[x];
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
        for (int i = 0; i < this.size; i++) {
            if(parent[i] == rootX){
                parent[i] = rootY;
            }
        }
    }
}
