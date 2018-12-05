package com.ccq.springbootkafka.algorithm.tree.test;

import java.util.ArrayList;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/5 13:31
 ***@Version 1.0.0
 ********************************/
public class BSTTree<E extends Comparable<E>> {

    /**
     * 根结点
     */
    private TreeNode<E> root;

    /**
     * 结点个数
     */
    private int size;

    public BSTTree() {
        this.root = null;
        size = 0;
    }

    public BSTTree(E[] objs) {
        for (E obj : objs) {
            insert(obj);
        }
    }

    /**
     * 结点结构定义
     *
     * @param <E> 结点泛型
     */
    private class TreeNode<E extends Comparable<E>> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E element) {
            this.element = element;
        }

        public TreeNode(TreeNode<E> node) {
            this.left = node.left;
            this.right = node.right;
            this.element = node.element;
        }
    }

    /**
     * 插入结点
     *
     * @param e 值
     */
    public void insert(E e) {
        root = insert(root, e);
    }

    /**
     * 向以node为根结点的二叉搜索树中，插入结点e，使用递归算法
     * 返回插入新结点后的二叉搜索树的根
     *
     * @param node 当前树的根结点
     * @param e    值
     * @return 插入新结点后 二叉搜索树的根
     */
    private TreeNode<E> insert(TreeNode<E> node, E e) {
        if (node == null) {
            size++;
            return new TreeNode<>(e);
        }

        if (e.compareTo(node.element) < 0) {
            node.left = insert(node.left, e);
        } else if (e.compareTo(node.element) > 0) {
            node.right = insert(node.right, e);
        } else {
            assert e.compareTo(node.element) == 0;
        }
        return node;
    }

    /**
     * 查询结点e
     *
     * @param e 值
     * @return true|false
     */
    public boolean search(E e) {
        return search(root, e);
    }

    /**
     * 在以node为根结点的二叉搜索树中，查找结点e，使用递归算法
     * 查找成功返回true，否则返回false
     *
     * @param node 当前树的根结点
     * @param e    值
     * @return true|false
     */
    private boolean search(TreeNode<E> node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.element) < 0) {
            return search(node.left, e);
        } else if (e.compareTo(node.element) > 0) {
            return search(node.right, e);
        } else {
            assert e.compareTo(node.element) == 0;
            return true;
        }
    }

    /**
     * 删除结点e
     *
     * @param e 值
     * @return true|false
     */
    public boolean delete(E e) {
        if (!search(e)) {
            return false;
        }
        root = delete(root, e);
        return true;
    }

    /**
     * 删除以node为根的二叉搜索树中的结点e
     * 返回删除结点后新的二分搜索树的根
     *
     * @param node 结点
     * @param e    值
     * @return 结点
     */
    private TreeNode<E> delete(TreeNode<E> node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.element) < 0) {
            node.left = delete(node.left, e);
            return node;
        } else if (e.compareTo(node.element) > 0) {
            node.right = delete(node.right, e);
            return node;
        } else {
            assert e.compareTo(node.element) == 0;

            // 待删除结点的左结点为null，只有右结点，直接返回右结点
            if (node.left == null) {
                TreeNode<E> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除结点的右结点为null，只有左结点，直接返回左结点
            if (node.right == null) {
                TreeNode<E> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除结点的左右结点都不为null时
            // 方法1、找到比待删除结点大的最小结点，也就是待删除结点的后继，即右子树结点的最小结点
            // 方法2、找到比待删除结点小的最大结点，也就是待删除节点的前驱，即左子树结点的最大结点

            // 实现使用方法1
            // 注意：
            TreeNode<E> successor = new TreeNode<>(getMinTreeNode(node.right));

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * 获取以node为根的最小结点
     *
     * @param node 结点
     * @return treeNode
     */
    private TreeNode<E> getMinTreeNode(TreeNode<E> node) {
        if (node.left == null) {
            return node;
        }
        return getMinTreeNode(node.left);
    }

    /**
     * 删除以node结点为根的最小结点
     * 返回删除结点后新的根
     *
     * @param node 结点
     * @return treeNode
     */
    private TreeNode<E> removeMin(TreeNode<E> node) {

        if (node.left == null) {
            TreeNode<E> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 中序遍历
     */
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * 是否是二叉搜索树
     *
     * @return true|false
     */
    public boolean isBST() {
        // 二叉搜索树中序遍历一定是递增的
        List<E> list = new ArrayList<>();
        inorder(root, list);
        E pre = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(pre) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取二叉搜索树的中序遍历List
     *
     * @param root 根结点
     * @param list 中序遍历结果
     */
    private void inorder(TreeNode<E> root, List<E> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.element);
        inorder(root.right, list);
    }
}
