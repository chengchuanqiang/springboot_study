package com.ccq.springbootkafka.algorithm.tree.test;


import com.ccq.springbootkafka.algorithm.tree.BinaryTree;
import com.ccq.springbootkafka.algorithm.tree.impl.BinarySearchTree;
import com.ccq.springbootkafka.algorithm.tree.impl.BinarySearchTree2;

/********************************
 *** 测试类
 ***@Author chengchuanqiang
 ***@Date 2018/12/6 19:33
 ***@Version 1.0.0
 ********************************/
public class Test {
    public static void main(String[] args) {
        Integer[] nums = {2, 4, 3, 5, 9, 8, 7, 5};

        // 递归实现二叉搜索树测试
        BinaryTree<Integer> binarySearchTree = new BinarySearchTree<>(nums);
        test(binarySearchTree);

        // 非递归实现二叉搜索树
        BinarySearchTree2<Integer> binarySearchTree2 = new BinarySearchTree2<>(nums);
        test(binarySearchTree2);

    }


    public static void test(BinaryTree<Integer> bst) {

        bst.inorder();
        bst.preorder();
        bst.postorder();

        System.out.println("search 2 is : " + bst.search(2));
        System.out.println("search 22 is : " + bst.search(22));

        System.out.println("delete 1 is : " + bst.delete(1));
        System.out.println("delete 2 is : " + bst.delete(2));
        System.out.println("is BST : " + bst.isBST());
        bst.inorder();

        System.out.println("delete 7 is : " + bst.delete(7));
        System.out.println("is BST : " + bst.isBST());
        bst.inorder();

        System.out.println("isEmpty : " + bst.isEmpty());
    }
}
