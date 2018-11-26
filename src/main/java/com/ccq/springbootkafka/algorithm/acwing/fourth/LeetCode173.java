package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/26 18:46
 ***@Version 1.0.0
 ********************************/
public class LeetCode173 {
}

class BSTIterator {

    private List<Integer> list = new ArrayList<>();
    private int cursor = 0;

    public BSTIterator(TreeNode root) {
        inorder(root, list);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return cursor != list.size();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        return list.get(cursor++);
    }
}

