package com.ccq.springbootkafka.algorithm.segmentTree.test;


import java.util.Scanner;

/********************************
 *** HDU I Hate It
 ***@Author chengchuanqiang
 ***@Date 2018/7/21 11:34
 ***@Version 1.0.0
 ********************************/
public class Main1754 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            int m = input.nextInt();
            Integer[] nums = new Integer[n];
            for (int i = 0; i < n; i++) {
                nums[i] = input.nextInt();
            }
            SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> Math.max(a, b));

            while ((m--) > 0) {
                String op = input.next();
                if ("Q".equals(op)) {
                    System.out.println(segmentTree.query(input.nextInt() - 1, input.nextInt() - 1));
                } else if ("U".equals(op)) {
                    segmentTree.update(input.nextInt() - 1, input.nextInt());
                }
                //System.out.println(segmentTree.toString());
            }
        }
    }

    private static class SegmentTree<E> {

        private E[] data;
        private E[] tree;
        private Merger<E> merger;

        SegmentTree(E[] data, Merger<E> merger) {
            this.data = data;
            this.merger = merger;
            tree = (E[]) new Object[data.length * 4];
            buildTree(0, 0, data.length - 1);
        }

        // 创建线段树
        private void buildTree(int treeIndex, int l, int r) {
            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }
            int mid = l + (r - l) / 2;
            buildTree(treeIndex * 2 + 1, l, mid);
            buildTree(treeIndex * 2 + 2, mid + 1, r);

            tree[treeIndex] = merger.merge(tree[treeIndex * 2 + 1], tree[treeIndex * 2 + 2]);
        }

        // 查找[queryL,queryR]区间进行merge操作的值
        private E query(int queryL, int queryR) {
            return query(0, 0, data.length - 1, queryL, queryR);
        }

        private E query(int treeIndex, int l, int r, int queryL, int queryR) {
            if (l == queryL && r == queryR) {
                return tree[treeIndex];
            }
            int mid = l + (r - l) / 2;
            if (queryL >= mid + 1) {
                return query(treeIndex * 2 + 2, mid + 1, r, queryL, queryR);
            } else if (queryR <= mid) {
                return query(treeIndex * 2 + 1, l, mid, queryL, queryR);
            } else {
                E leftRes = query(treeIndex * 2 + 1, l, mid, queryL, mid);
                E rightRes = query(treeIndex * 2 + 2, mid + 1, r, mid + 1, queryR);
                return merger.merge(leftRes, rightRes);
            }
        }

        // 更新[index]实行merge操作
        public void update(int index, E updateVal) {
            update(0, 0, data.length - 1, index, updateVal);
        }

        private void update(int treeIndex, int l, int r, int index, E updateVal) {
            if (l == r) {
                tree[treeIndex] = updateVal;
                return;
            }

            int mid = l + (r - l) / 2;
            if (index <= mid) {
                update(treeIndex * 2 + 1, l, mid, index, updateVal);
            } else {
                update(treeIndex * 2 + 2, mid + 1, r, index, updateVal);
            }
            tree[treeIndex] = merger.merge(tree[treeIndex * 2 + 1], tree[treeIndex * 2 + 2]);
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

    private interface Merger<E> {
        E merge(E a, E b);
    }
}