package com.ccq.springbootkafka.algorithm.tree.impl;

import com.ccq.springbootkafka.algorithm.tree.BinaryTree;
import com.ccq.springbootkafka.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/********************************
 *** 二叉搜索树非递归实现
 ***@Author chengchuanqiang
 ***@Date 2018/12/6 19:46
 ***@Version 1.0.0
 ********************************/
public class BinarySearchTree2<E extends Comparable<E>> implements BinaryTree<E> {

    /**
     * 根结点
     */
    private TreeNode<E> root;

    /**
     * 结点个数
     */
    private int size;

    public BinarySearchTree2() {
        this.root = null;
        size = 0;
    }

    public BinarySearchTree2(E[] objs) {
        for (E obj : objs) {
            insert(obj);
        }
    }

    private TreeNode<E> createTreeNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = createTreeNode(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }
            if (e.compareTo(parent.element) < 0) {
                parent.left = createTreeNode(e);
            } else {
                parent.right = createTreeNode(e);
            }
        }
        size++;
        return true;
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }
        // 没有找到e
        if (current == null) {
            return false;
        }

        if (current.left == null) {
            // case1:没有左孩子
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // case:有左孩子
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    @Override
    public void inorder() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.element + " ");
                node = node.right;
            }
        }
        System.out.println();
    }

    @Override
    public void preorder() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                System.out.print(node.element + " ");
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
        System.out.println();
    }

    @Override
    public void postorder() {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                output.push(node);
                stack.push(node);
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }
        while (!output.isEmpty()) {
            System.out.print(output.pop().element + " ");
        }
        System.out.println();

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isBST() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<E> {

        private List<E> list = new ArrayList<>();
        private int cursor = 0;

        InorderIterator() {
            inorder(root, list);
        }

        private void inorder(TreeNode<E> root, List<E> list) {
            if (root == null) {
                return;
            }
            inorder(root.left, list);
            list.add(root.element);
            inorder(root.right, list);
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            return list.get(cursor++);
        }

        @Override
        public void remove() {
            delete(list.get(cursor));
            list.clear();
            inorder(root, list);
        }
    }

    @Override
    public TreeNode<E> getRoot() {
        return this.root;
    }

}
