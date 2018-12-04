package com.ccq.springbootkafka.algorithm.tree.avl;

import com.ccq.springbootkafka.algorithm.tree.bst.BST;

import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/3 9:37
 ***@Version 1.0.0
 ********************************/
public class AVLTree<E extends Comparable<E>> extends BST<E> {

    public AVLTree() {
    }

    public AVLTree(E[] objs) {
        super(objs);
    }

    @Override
    protected AVLTreeNode<E> createTreeNode(E e) {
        return new AVLTreeNode<E>(e);
    }

    private void updateHeight(AVLTreeNode<E> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.height = 0;
        } else if (node.getLeft() == null) {
            node.height = ((AVLTreeNode<E>) node.getRight()).getHeight() + 1;
        } else if (node.getRight() == null) {
            node.height = ((AVLTreeNode<E>) node.getLeft()).getHeight() + 1;
        } else {
            node.height = Math.max(((AVLTreeNode<E>) node.getLeft()).getHeight(), ((AVLTreeNode<E>) node.getRight()).getHeight()) + 1;
        }
    }

    @Override
    public boolean insert(E e) {
        boolean success = super.insert(e);
        if (!success) {
            return false;
        } else {
            balancePath(e);
        }
        return true;

    }

    private void balancePath(E e) {
        List<TreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> node = (AVLTreeNode<E>) path.get(i);
            updateHeight(node);
            AVLTreeNode<E> parent = node == root ? null : (AVLTreeNode<E>) path.get(i - 1);

            switch (balanceFactor(node)) {
                case -2:
                    if (balanceFactor((AVLTreeNode<E>) node.getLeft()) <= 0) {
                        balanceLL(node, parent);
                    } else {
                        balanceLR(node, parent);
                    }
                    break;
                case 2:
                    if (balanceFactor((AVLTreeNode<E>) node.getRight()) >= 0) {
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
        TreeNode<E> B = A.getLeft();

        if (A == root) {
            root = B;
        } else {
            if (parent.getLeft() == A) {
                parent.setLeft(B);
            } else {
                parent.setRight(B);
            }
        }

        A.setLeft(B.getRight());
        B.setRight(A);

        updateHeight(A);
        updateHeight((AVLTreeNode<E>) B);

    }

    /**
     * LR
     *
     * @param A         需要旋转的节点
     * @param parentOfA 需要旋转节点的父节点
     */
    private void balanceLR(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        TreeNode<E> B = A.getLeft();
        TreeNode<E> C = B.getRight();
        if (A == root) {
            root = C;
        } else {
            if (parentOfA.getLeft() == A) {
                parentOfA.setLeft(C);
            } else {
                parentOfA.setRight(C);
            }
        }
        A.setLeft(C.getRight());
        B.setRight(C.getLeft());
        C.setLeft(B);
        C.setRight(A);

        updateHeight(A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    /**
     * RR
     *
     * @param A      需要旋转的节点
     * @param parent 需要旋转节点的父节点
     */
    private void balanceRR(AVLTreeNode<E> A, AVLTreeNode<E> parent) {
        TreeNode<E> B = A.getRight();

        if (A == root) {
            root = B;
        } else {
            if (parent.getLeft() == A) {
                parent.setLeft(B);
            } else {
                parent.setRight(B);
            }
        }

        A.setRight(B.getLeft());
        B.setLeft(A);

        updateHeight(A);
        updateHeight((AVLTreeNode<E>) B);
    }

    /**
     * RL
     *
     * @param A         需要旋转的节点
     * @param parentOfA 需要旋转节点的父节点
     */
    private void balanceRL(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        TreeNode<E> B = A.getRight();
        TreeNode<E> C = B.getLeft();

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.getLeft() == A) {
                parentOfA.setLeft(C);
            } else {
                parentOfA.setRight(C);
            }
        }

        A.setRight(C.getLeft());
        B.setLeft(C.getRight());
        C.setLeft(A);
        C.setRight(B);

        updateHeight(A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.getRight() == null) {
            return -node.height;
        } else if (node.getLeft() == null) {
            return node.height;
        } else {
            return ((AVLTreeNode<E>) node.getRight()).height - ((AVLTreeNode<E>) node.getLeft()).height;
        }
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.getElement()) < 0) {
                parent = current;
                current = current.getLeft();
            } else if (e.compareTo(current.getElement()) > 0) {
                parent = current;
                current = current.getRight();
            } else {
                break;
            }
        }
        // 没有找到e
        if (current == null) {
            return false;
        }

        if (current.getLeft() == null) {
            // case1:没有左孩子
            if (parent == null) {
                root = current.getRight();
            } else {
                if (e.compareTo(parent.getElement()) < 0) {
                    parent.setLeft(current.getRight());
                } else {
                    parent.setRight(current.getRight());
                }
                balancePath(parent.getElement());
            }
        } else {
            // case:有左孩子
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.getLeft();
            while (rightMost.getRight() != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.getRight();
            }

            current.setElement(rightMost.getElement());
            if (parentOfRightMost.getRight() == rightMost) {
                parentOfRightMost.setRight(rightMost.getLeft());
            } else {
                parentOfRightMost.setLeft(rightMost.getLeft());
            }
            balancePath(parentOfRightMost.getElement());
        }
        size--;
        return true;
    }

    protected static class AVLTreeNode<E extends Comparable<E>> extends BST.TreeNode<E> {

        protected int height = 0;

        public AVLTreeNode(E element) {
            super(element);
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }


}
