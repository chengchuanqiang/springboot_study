package com.ccq.springbootkafka.algorithm.tree;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/6 17:53
 ***@Version 1.0.0
 ********************************/
public class TreeNode<E extends Comparable<E>> {

    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E element) {
        this.element = element;
    }

    public TreeNode(TreeNode<E> node) {
        this.left = node.left;
        this.right = node.right;
        this.element = node.element;
    }
}
