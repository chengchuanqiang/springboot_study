package com.ccq.springbootkafka.algorithm.tree.bst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/********************************
 *** 二叉查找树实现
 ***@Author chengchuanqiang
 ***@Date 2018/11/26 9:39
 ***@Version 1.0.0
 ********************************/
public class BST<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    public BST() {
    }

    public BST(E[] objs) {
        for (E obj : objs) {
            insert(obj);
        }
    }

    public static class TreeNode<E extends Comparable<E>> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public TreeNode<E> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<E> left) {
            this.left = left;
        }

        public TreeNode<E> getRight() {
            return right;
        }

        public void setRight(TreeNode<E> right) {
            this.right = right;
        }
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

    protected TreeNode<E> createTreeNode(E e) {
        return new TreeNode<>(e);
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
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(TreeNode<E> node) {

        if (node == null) {
            return 0;
        }

        return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
    }

    public List<TreeNode<E>> path(E e) {
        List<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list;
    }

    @Override
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

    @Override
    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
        System.out.println();
    }

    private void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
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

    private void inorder(TreeNode<E> root, List<E> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.element);
        inorder(root.right, list);
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

    public TreeNode<E> getRoot() {
        return root;
    }
}
