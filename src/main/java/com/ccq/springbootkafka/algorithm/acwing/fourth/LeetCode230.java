package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/29 9:46
 ***@Version 1.0.0
 ********************************/
public class LeetCode230 {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        orderDFS(root, res);
        return res.get(k - 1);
    }

    public void orderDFS(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        orderDFS(root.left, res);
        res.add(root.val);
        orderDFS(root.right, res);
    }
}
