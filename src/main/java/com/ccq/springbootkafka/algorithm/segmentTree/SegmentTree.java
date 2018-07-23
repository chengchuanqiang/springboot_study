package com.ccq.springbootkafka.algorithm.segmentTree;

/********************************
 *** 线段树实体
 ***@Author chengchuanqiang
 ***@Date 2018/7/21 9:18
 ***@Version 1.0.0
 ********************************/
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] data, Merger<E> merger) {
        this.data = data;
        this.merger = merger;
        tree = (E[]) new Object[data.length * 4];
        buildTree(0, 0, data.length - 1);
    }

    // 创建线段树
    private void buildTree(int treeIndex, int l, int r) {
        if (l < 0 || r < 0 || l > r) {
            throw new IllegalArgumentException();
        }
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildTree(leftChild, l, mid);
        buildTree(rightChild, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
    }

    // 查找[queryL,queryR]区间进行merge操作的值
    public E query(int queryL, int queryR) {
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        if (l < 0 || r < 0 || l > r || queryL < l || queryR > r || queryL > queryR) {
            throw new IllegalArgumentException();
        }

        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightChild, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftChild, l, mid, queryL, queryR);
        } else {
            return merger.merge(query(leftChild, l, mid, queryL, mid), query(rightChild, mid + 1, r, mid + 1, queryR));
        }
    }

    // 更新[index]实行merge操作
    public void update(int index, E updateVal) {
        data[index] = updateVal;
        update(0, 0, data.length - 1, index, updateVal);
    }

    private void update(int treeIndex, int l, int r, int index, E updateVal) {
        if (l < 0 || r < 0 || l > r || index < l || index > r) {
            throw new IllegalArgumentException();
        }

        if (l == r) {
            tree[treeIndex] = updateVal;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);

        if (index <= mid) {
            update(leftChild, l, mid, index, updateVal);
        } else {
            update(rightChild, mid + 1, r, index, updateVal);
        }
        tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
    }

      // 获取treeIndex在数组中的左孩子索引
    private int getLeftChild(int treeIndex) {
        return treeIndex * 2 + 1;
    }

    // 获取treeIndex在数组中的右孩子索引
    private int getRightChild(int treeIndex) {
        return treeIndex * 2 + 2;
    }

    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder();
        sbf.append("[");
        for (E e : tree) {
            if (e != null) {
                sbf.append(e + " ");
            } else {
                sbf.append("#" + " ");
            }
        }
        sbf.append("]");
        return sbf.toString();
    }
}
