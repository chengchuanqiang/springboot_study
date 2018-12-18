package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/20 18:22
 ***@Version 1.0.0
 ********************************/
public class LeetCode98 {

    public boolean isValidBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        orderDFS(root, res);
        if (res.size() > 1) {
            for (int i = 1; i < res.size(); i++) {
                if (res.get(i) <= res.get(i - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void orderDFS(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        orderDFS(root.left, res);
        res.add(root.val);
        orderDFS(root.right, res);
    }

    public static void main(String[] args) {
        LeetCode98 test = new LeetCode98();

        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println(test.isValidBST(root1));

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println(test.isValidBST(root2));

        TreeNode root3 = new TreeNode(10);
        root3.left = new TreeNode(5);
        root3.right = new TreeNode(15);
        root3.right.left = new TreeNode(6);
        root3.right.right = new TreeNode(20);
        System.out.println(test.isValidBST(root3));

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
