package com.ccq.springbootkafka.algorithm.searchTree;

/********************************
 *** 二分搜索树
 ***@Author chengchuanqiang
 ***@Date 2018/7/27 9:48
 ***@Version 1.0.0
 ********************************/
public class BinarySearchTree {


    private static Node root;

    private static void build(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            root = add(root, nums[i]);
        }
    }

    private static Node add(Node root, int val){
        if(root == null){
            return new Node(val);
        }
        if(root.val > val){
            root.left = add(root.left, val);
        }else if(root.val < val){
            root.right = add(root.right, val);
        }
        return root;
    }

    static class Node {
        private Node left;
        private Node right;
        private int val;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,4,5,8,9,7,6};
        build(nums);

        System.out.println(root);
    }
}
