package com.ccq.springbootkafka.algorithm.tree;


/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/6 17:59
 ***@Version 1.0.0
 ********************************/
public class AVLTreeNode<E extends Comparable<E>> extends TreeNode<E> {

    public int height = 0;

    public AVLTreeNode(E element) {
        super(element);
    }

    public AVLTreeNode(TreeNode<E> node) {
        super(node);
    }
}
