//package com.ccq.springbootkafka.algorithm.tree.avl;
//
//import com.ccq.springbootkafka.algorithm.tree.AVLTreeNode;
//import com.ccq.springbootkafka.algorithm.tree.TreeNode;
//import BinarySearchTree;
//
//import java.util.List;
//
///********************************
// ***
// ***@Author chengchuanqiang
// ***@Date 2018/12/3 9:37
// ***@Version 1.0.0
// ********************************/
//public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
//
//    public AVLTree() {
//    }
//
//    public AVLTree(E[] objs) {
//        super(objs);
//    }
//
//    @Override
//    protected AVLTreeNode<E> createTreeNode(E e) {
//        return new AVLTreeNode<>(e);
//    }
//
//    private void updateHeight(AVLTreeNode<E> node) {
//        if (node.left == null && node.right == null) {
//            node.height = 0;
//        } else if (node.left == null) {
//            node.height = ((AVLTreeNode<E>) node.right).height + 1;
//        } else if (node.right == null) {
//            node.height = ((AVLTreeNode<E>) node.left).height + 1;
//        } else {
//            node.height = Math.max(((AVLTreeNode<E>) node.left).height, ((AVLTreeNode<E>) node.right).height) + 1;
//        }
//    }
//
//    @Override
//    public boolean insert(E e) {
//        boolean success = super.insert(e);
//        if (!success) {
//            return false;
//        } else {
//            balancePath(e);
//        }
//        return true;
//
//    }
//
//    private void balancePath(E e) {
//        List<TreeNode<E>> path = path(e);
//        for (int i = path.size() - 1; i >= 0; i--) {
//            AVLTreeNode<E> node = (AVLTreeNode<E>) path.get(i);
//            updateHeight(node);
//            AVLTreeNode<E> parent = node == root ? null : (AVLTreeNode<E>) path.get(i - 1);
//
//            switch (balanceFactor(node)) {
//                case -2:
//                    if (balanceFactor((AVLTreeNode<E>) node.left) <= 0) {
//                        balanceLL(node, parent);
//                    } else {
//                        balanceLR(node, parent);
//                    }
//                    break;
//                case 2:
//                    if (balanceFactor((AVLTreeNode<E>) node.right) >= 0) {
//                        balanceRR(node, parent);
//                    } else {
//                        balanceRL(node, parent);
//                    }
//                    break;
//                default:
//                    break;
//
//            }
//        }
//    }
//
//    /**
//     * LL
//     *
//     * @param A      需要旋转的节点
//     * @param parent 需要旋转节点的父节点
//     */
//    private void balanceLL(AVLTreeNode<E> A, AVLTreeNode<E> parent) {
//        TreeNode<E> B = A.left;
//
//        if (A == root) {
//            root = B;
//        } else {
//            if (parent.left == A) {
//                parent.left = B;
//            } else {
//                parent.right = B;
//            }
//        }
//
//        A.left = B.right;
//        B.right = A;
//
//        updateHeight(A);
//        updateHeight((AVLTreeNode<E>) B);
//
//    }
//
//    /**
//     * LR
//     *
//     * @param A         需要旋转的节点
//     * @param parentOfA 需要旋转节点的父节点
//     */
//    private void balanceLR(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
//        TreeNode<E> B = A.left;
//        TreeNode<E> C = B.right;
//        if (A == root) {
//            root = C;
//        } else {
//            if (parentOfA.left == A) {
//                parentOfA.left = C;
//            } else {
//                parentOfA.right = C;
//            }
//        }
//        A.left = C.right;
//        B.right = C.left;
//        C.left = B;
//        C.right = A;
//
//        updateHeight(A);
//        updateHeight((AVLTreeNode<E>) B);
//        updateHeight((AVLTreeNode<E>) C);
//    }
//
//    /**
//     * RR
//     *
//     * @param A      需要旋转的节点
//     * @param parent 需要旋转节点的父节点
//     */
//    private void balanceRR(AVLTreeNode<E> A, AVLTreeNode<E> parent) {
//        TreeNode<E> B = A.right;
//
//        if (A == root) {
//            root = B;
//        } else {
//            if (parent.left == A) {
//                parent.left = B;
//            } else {
//                parent.right = B;
//            }
//        }
//
//        A.right = B.left;
//        B.left = A;
//
//        updateHeight(A);
//        updateHeight((AVLTreeNode<E>) B);
//    }
//
//    /**
//     * RL
//     *
//     * @param A         需要旋转的节点
//     * @param parentOfA 需要旋转节点的父节点
//     */
//    private void balanceRL(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
//        TreeNode<E> B = A.right;
//        TreeNode<E> C = B.left;
//
//        if (A == root) {
//            root = C;
//        } else {
//            if (parentOfA.left == A) {
//                parentOfA.left = C;
//            } else {
//                parentOfA.right = C;
//            }
//        }
//
//        A.right = C.left;
//        B.left = C.right;
//        C.left = A;
//        C.right = B;
//
//        updateHeight(A);
//        updateHeight((AVLTreeNode<E>) B);
//        updateHeight((AVLTreeNode<E>) C);
//    }
//
//    private int balanceFactor(AVLTreeNode<E> node) {
//        if (node.right == null) {
//            return -node.height;
//        } else if (node.left == null) {
//            return node.height;
//        } else {
//            return ((AVLTreeNode<E>) node.right).height - ((AVLTreeNode<E>) node.left).height;
//        }
//    }
//
//    @Override
//    public boolean delete(E e) {
//        TreeNode<E> parent = null;
//        TreeNode<E> current = root;
//        while (current != null) {
//            if (e.compareTo(current.element) < 0) {
//                parent = current;
//                current = current.left;
//            } else if (e.compareTo(current.element) > 0) {
//                parent = current;
//                current = current.right;
//            } else {
//                break;
//            }
//        }
//        // 没有找到e
//        if (current == null) {
//            return false;
//        }
//
//        if (current.left == null) {
//            // case1:没有左孩子
//            if (parent == null) {
//                root = current.right;
//            } else {
//                if (e.compareTo(parent.element) < 0) {
//                    parent.left = current.right;
//                } else {
//                    parent.right = current.right;
//                }
//                balancePath(parent.element);
//            }
//        } else {
//            // case:有左孩子
//            TreeNode<E> parentOfRightMost = current;
//            TreeNode<E> rightMost = current.left;
//            while (rightMost.right != null) {
//                parentOfRightMost = rightMost;
//                rightMost = rightMost.right;
//            }
//
//            current.element = rightMost.element;
//            if (parentOfRightMost.right == rightMost) {
//                parentOfRightMost.right = rightMost.left;
//            } else {
//                parentOfRightMost.left = rightMost.left;
//            }
//            balancePath(parentOfRightMost.element);
//        }
//        size--;
//        return true;
//    }
//
//}
