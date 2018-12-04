package com.ccq.springbootkafka.algorithm.tree.avl;

import com.ccq.springbootkafka.algorithm.tree.bst.BST;

import java.util.Iterator;

/********************************
 *** 二叉查找树测试
 ***@Author chengchuanqiang
 ***@Date 2018/11/26 9:56
 ***@Version 1.0.0
 ********************************/
public class AVLTreeTest {

    public static void main(String[] args) {
        Integer[] objs = {2, 4, 3, 5, 9, 8, 7, 5};
        BST<Integer> avlTree = new AVLTree<>(objs);
        avlTree.inorder();
        System.out.println("tree height is : " + avlTree.getHeight());

        Iterator<Integer> iterator = avlTree.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();


        System.out.println("search 2 is : " + avlTree.search(2));
        System.out.println("search 22 is : " + avlTree.search(22));

//        System.out.println("delete 1 is : " + avlTree.delete(1));
//        System.out.println("delete 2 is : " + avlTree.delete(2));
//        System.out.println("is AVLTree : " + avlTree.isBST());
//        avlTree.inorder();

//        System.out.println("delete 7 is : " + avlTree.delete(7));
//        System.out.println("is AVLTree : " + avlTree.isBST());
//        avlTree.inorder();

    }

}
