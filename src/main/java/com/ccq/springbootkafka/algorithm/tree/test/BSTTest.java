package com.ccq.springbootkafka.algorithm.tree.test;

/********************************
 *** 二叉查找树测试
 ***@Author chengchuanqiang
 ***@Date 2018/11/26 9:56
 ***@Version 1.0.0
 ********************************/
public class BSTTest {

    public static void main(String[] args) {
        Integer[] objs = {2, 4, 3, 5, 9, 8, 7, 5};
        BSTTree<Integer> bst = new BSTTree<>(objs);
        bst.inorder();

        System.out.println("search 2 is : " + bst.search(2));
        System.out.println("search 22 is : " + bst.search(22));

        System.out.println("delete 1 is : " + bst.delete(1));
        System.out.println("delete 2 is : " + bst.delete(2));
        System.out.println("is BST : " + bst.isBST());
        bst.inorder();

        System.out.println("delete 7 is : " + bst.delete(7));
        System.out.println("is BST : " + bst.isBST());
        bst.inorder();

    }

}