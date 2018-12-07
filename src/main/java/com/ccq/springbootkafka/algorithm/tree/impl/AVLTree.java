package com.ccq.springbootkafka.algorithm.tree.impl;

import com.ccq.springbootkafka.algorithm.tree.BinaryTree;
import com.ccq.springbootkafka.algorithm.tree.AVLTreeNode;
import com.ccq.springbootkafka.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/6 19:12
 ***@Version 1.0.0
 ********************************/
public class AVLTree<E extends Comparable<E>> implements BinaryTree<E> {

    private AVLTreeNode<E> root;
    private int size;

    public AVLTree() {
        this.root = null;
        size = 0;
    }

    public AVLTree(E[] objs) {
        for (E obj : objs) {
            insert(obj);
        }
    }

    private AVLTreeNode<E> createTreeNode(E e) {
        return new AVLTreeNode<>(e);
    }

    @Override
    public boolean search(E e) {
        AVLTreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = (AVLTreeNode<E>) current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = (AVLTreeNode<E>) current.right;
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
            AVLTreeNode<E> parent = null;
            AVLTreeNode<E> current = root;

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = (AVLTreeNode<E>) current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = (AVLTreeNode<E>) current.right;
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
        balancePath(e);
        return true;
    }

    private void updateHeight(AVLTreeNode<E> node) {
        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left == null) {
            node.height = ((AVLTreeNode<E>)node.right).height + 1;
        } else if (node.right == null) {
            node.height = ((AVLTreeNode<E>)node.left).height + 1;
        } else {
            node.height = Math.max(((AVLTreeNode<E>)node.left).height, ((AVLTreeNode<E>)node.right).height) + 1;
        }
    }

    private List<AVLTreeNode<E>> path(E e) {
        AVLTreeNode<E> current = root;
        List<AVLTreeNode<E>> list = new ArrayList<>();

        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = (AVLTreeNode<E>) current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = (AVLTreeNode<E>) current.right;
            } else {
                break;
            }
        }

        return list;
    }

    private void balancePath(E e) {
        List<AVLTreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> node = path.get(i);
            updateHeight(node);
            AVLTreeNode<E> parent = node == root ? null : path.get(i - 1);

            switch (balanceFactor(node)) {
                case -2:
                    if (balanceFactor((AVLTreeNode<E>) node.left) <= 0) {
                        balanceLL(node, parent);
                    } else {
                        balanceLR(node, parent);
                    }
                    break;
                case 2:
                    if (balanceFactor((AVLTreeNode<E>) node.right) >= 0) {
                        balanceRR(node, parent);
                    } else {
                        balanceRL(node, parent);
                    }
                    break;
                default:
                    break;

            }
        }
    }

    /**
     * LL
     *
     * @param A      需要旋转的节点
     * @param parent 需要旋转节点的父节点
     */
    private void balanceLL(AVLTreeNode<E> A, AVLTreeNode<E> parent) {
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.left;

        if (A == root) {
            root = B;
        } else {
            if (parent.left == A) {
                parent.left = B;
            } else {
                parent.right = B;
            }
        }

        A.left = B.right;
        B.right = A;

        updateHeight(A);
        updateHeight(B);

    }

    /**
     * LR
     *
     * @param A         需要旋转的节点
     * @param parentOfA 需要旋转节点的父节点
     */
    private void balanceLR(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.left;
        AVLTreeNode<E> C = (AVLTreeNode<E>) B.right;
        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }
        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;

        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
    }

    /**
     * RR
     *
     * @param A      需要旋转的节点
     * @param parent 需要旋转节点的父节点
     */
    private void balanceRR(AVLTreeNode<E> A, AVLTreeNode<E> parent) {
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.right;

        if (A == root) {
            root = B;
        } else {
            if (parent.left == A) {
                parent.left = B;
            } else {
                parent.right = B;
            }
        }

        A.right = B.left;
        B.left = A;

        updateHeight(A);
        updateHeight(B);
    }

    /**
     * RL
     *
     * @param A         需要旋转的节点
     * @param parentOfA 需要旋转节点的父节点
     */
    private void balanceRL(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.right;
        AVLTreeNode<E> C = (AVLTreeNode<E>) B.left;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;

        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
    }

    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.right == null) {
            return -node.height;
        } else if (node.left == null) {
            return node.height;
        } else {
            return ((AVLTreeNode<E>)node.right).height - ((AVLTreeNode<E>)node.left).height;
        }
    }


    @Override
    public boolean delete(E e) {
        AVLTreeNode<E> parent = null;
        AVLTreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = (AVLTreeNode<E>) current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = (AVLTreeNode<E>) current.right;
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
                root = (AVLTreeNode<E>) current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
                balancePath(parent.element);
            }
        } else {
            // case:有左孩子
            AVLTreeNode<E> parentOfRightMost = current;
            AVLTreeNode<E> rightMost = (AVLTreeNode<E>) current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = (AVLTreeNode<E>) rightMost.right;
            }

            current.element = rightMost.element;
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
            balancePath(parentOfRightMost.element);
        }
        size--;
        return true;
    }

    @Override
    public void inorder() {

    }

    @Override
    public void preorder() {

    }

    @Override
    public void postorder() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isBST() {
        return false;
    }

    @Override
    public TreeNode<E> getRoot() {
        return root;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
