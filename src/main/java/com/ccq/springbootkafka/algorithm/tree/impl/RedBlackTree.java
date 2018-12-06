package com.ccq.springbootkafka.algorithm.tree.impl;

import com.ccq.springbootkafka.algorithm.tree.BinaryTree;
import com.ccq.springbootkafka.algorithm.tree.TreeNode;

import java.util.Iterator;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/6 19:12
 ***@Version 1.0.0
 ********************************/
public class RedBlackTree<E extends Comparable<E>> implements BinaryTree<E> {
    @Override
    public boolean search(E e) {
        return false;
    }

    @Override
    public boolean insert(E e) {
        return false;
    }

    @Override
    public boolean delete(E e) {
        return false;
    }

    @Override
    public void inorder() {

    }

    @Override
    public void preorder() {

    }

    @Override
    public void postorder() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isBST() {
        return false;
    }

    @Override
    public TreeNode<E> getRoot() {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
