package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/1/30 12:34
 ***@Version 1.0.0
 ********************************/
public class LeetCode589 {

    /**
     * 递归实现
     *
     * @param root 根结点
     * @return res
     */
    public List<Integer> preorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        preorder(root, res);
        return res;
    }

    private void preorder(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            preorder(root.children.get(i), res);
        }
    }

    /**
     * 遍历
     *
     * @param root 根节点
     * @return res
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            res.add(top.val);
            for (int i = top.children.size() - 1; i >= 0; i--) {
                stack.add(top.children.get(i));
            }
        }

        return res;
    }


}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
