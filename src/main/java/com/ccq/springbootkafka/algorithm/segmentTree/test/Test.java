package com.ccq.springbootkafka.algorithm.segmentTree.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/21 11:34
 ***@Version 1.0.0
 ********************************/
public class Test {

    public static void main(String[] args) {
        Integer[] nums = {1, 3, 2, 2, 4, 5, 6, 1};

        Merger<Integer> sumMerger = (a, b) -> a + b;
        Merger<Integer> minMerger = (a, b) -> Math.max(a, b);
        Merger<Integer> maxMerger = (a, b) -> Math.min(a, b);
        List<Merger<Integer>> mergers = new ArrayList<>();
        mergers.add(sumMerger);
        mergers.add(minMerger);
        mergers.add(maxMerger);
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, mergers);

        System.out.println(segmentTree.toString());
        System.out.println(Arrays.asList(segmentTree.query(0, 0)));

        segmentTree.update(0, 100);
        System.out.println(Arrays.asList(segmentTree.query(0, 7)));


        segmentTree.update(1, -100);
        System.out.println(Arrays.asList(segmentTree.query(0, 0)));
        System.out.println(Arrays.asList(segmentTree.query(0, 7)));
    }


    private static class SegmentTree<E> {

        private E[] data;
        private E[][] tree;
        private List<Merger<E>> mergers;

        public SegmentTree(E[] data, List<Merger<E>> mergers) {
            this.data = data;
            this.mergers = mergers;
            tree = (E[][]) new Object[data.length * 4][mergers.size()];
            buildTree(0, 0, data.length - 1);
        }

        // 创建线段树
        private void buildTree(int treeIndex, int l, int r) {
            if (l == r) {
                for (int i = 0; i < mergers.size(); i++) {
                    tree[treeIndex][i] = data[l];
                }
                return;
            }
            int mid = l + (r - l) / 2;
            buildTree(treeIndex * 2 + 1, l, mid);
            buildTree(treeIndex * 2 + 2, mid + 1, r);

            for (int i = 0; i < mergers.size(); i++) {
                tree[treeIndex][i] = mergers.get(i).merge(tree[treeIndex * 2 + 1][i], tree[treeIndex * 2 + 2][i]);
            }
        }

        // 查找[queryL,queryR]区间进行merge操作的值
        public E[] query(int queryL, int queryR) {
            return query(0, 0, data.length - 1, queryL, queryR);
        }

        private E[] query(int treeIndex, int l, int r, int queryL, int queryR) {
            if (l == queryL && r == queryR) {
                return tree[treeIndex];
            }
            int mid = l + (r - l) / 2;
            if (queryL >= mid + 1) {
                return query(treeIndex * 2 + 2, mid + 1, r, queryL, queryR);
            } else if (queryR <= mid) {
                return query(treeIndex * 2 + 1, l, mid, queryL, queryR);
            } else {
                E[] leftRes = query(treeIndex * 2 + 1, l, mid, queryL, mid);
                E[] rightRes = query(treeIndex * 2 + 2, mid + 1, r, mid + 1, queryR);
                for (int i = 0; i < mergers.size(); i++) {
                    tree[treeIndex][i] = mergers.get(i).merge(leftRes[i], rightRes[i]);
                }
                return tree[treeIndex];
            }
        }

        // 更新[index]实行merge操作
        public void update(int index, E updateVal) {
            update(0, 0, data.length - 1, index, updateVal);
        }

        private void update(int treeIndex, int l, int r, int index, E updateVal) {
            if (l == r) {
                for (int i = 0; i < mergers.size(); i++) {
                    tree[treeIndex][i] = mergers.get(i).merge(tree[treeIndex][i], updateVal);
                }
                return;
            }

            int mid = l + (r - l) / 2;
            if (index <= mid) {
                update(treeIndex * 2 + 1, l, mid, index, updateVal);
            } else {
                update(treeIndex * 2 + 2, mid + 1, r, index, updateVal);
            }
            for (int i = 0; i < mergers.size(); i++) {
                tree[treeIndex][i] = mergers.get(i).merge(tree[treeIndex * 2 + 1][i], tree[treeIndex * 2 + 2][i]);
            }
        }
    }

    private interface Merger<E> {
        E merge(E a, E b);
    }
}