package com.ccq.springbootkafka.algorithm.tree.bst;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/26 9:45
 ***@Version 1.0.0
 ********************************/
public abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean search(E e) {
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
    public boolean isEmpty() {
        return getSize() == 0;
    }
}
