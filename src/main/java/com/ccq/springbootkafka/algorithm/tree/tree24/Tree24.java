package com.ccq.springbootkafka.algorithm.tree.tree24;

import java.util.ArrayList;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/12 11:37
 ***@Version 1.0.0
 ********************************/
public class Tree24<E extends Comparable<E>> {

    private Tree24Node<E> root;
    private int size;

    public Tree24() {
    }

    public Tree24(E[] elements) {
        for (E e : elements) {
            insert(e);
        }
    }

    public boolean search(E e) {
        Tree24Node<E> current = root;
        while (current != null) {
            if (matched(e, current)) {
                return true;
            } else {
                current = getChildNode(e, current);
            }
        }
        return false;
    }

    private boolean matched(E e, Tree24Node<E> node) {
        for (E n : node.elements) {
            if (e.equals(n)) {
                return true;
            }
        }
        return false;
    }

    private Tree24Node<E> getChildNode(E e, Tree24Node<E> node) {
        if (node.elements.size() == 0) {
            return null;
        }

        int i = locate(e, node);
        if (i >= node.child.size()) {
            return null;
        }
        return node.child.get(i);
    }

    private int locate(E e, Tree24Node<E> node) {
        for (int i = 0; i < node.elements.size(); i++) {
            if (e.compareTo(node.elements.get(i)) <= 0) {
                return i;
            }
        }
        return node.elements.size();
    }

    public boolean insert(E e) {
        if (root == null) {
            root = new Tree24Node<>(e);
        } else {
            Tree24Node<E> leafNode = null;
            Tree24Node<E> current = root;

            while (current != null) {
                if (matched(e, current)) {
                    return false;
                } else {
                    leafNode = current;
                    current = getChildNode(e, current);
                }
            }
            insert(e, null, leafNode);
        }
        size++;
        return true;

    }

    private void insert(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u) {
        ArrayList<Tree24Node<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            if (u.elements.size() < 3) {
                insert23(e, rightChildOfe, u);
                break;
            } else {
                Tree24Node<E> v = new Tree24Node<>();
                E median = split(e, rightChildOfe, u, v);
                if (u == root) {
                    root = new Tree24Node<>(median);
                    root.child.add(u);
                    root.child.add(v);
                    break;
                } else {
                    e = median;
                    rightChildOfe = v;
                    u = path.get(i - 1);
                }
            }
        }
    }


    private void insert23(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> node) {
        int i = locate(e, node);
        node.elements.add(i, e);
        if (rightChildOfe != null) {
            node.child.add(i + 1, rightChildOfe);
        }
    }

    private E split(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u, Tree24Node<E> v) {
        v.elements.add(u.elements.remove(2));
        E median = u.elements.remove(1);

        if (u.child.size() > 0) {
            v.child.add(u.child.remove(2));
            v.child.add(u.child.remove(2));
        }

        if (e.compareTo(median) < 0) {
            insert23(e, rightChildOfe, u);
        } else {
            insert23(e, rightChildOfe, v);
        }

        return median;
    }


    private ArrayList<Tree24Node<E>> path(E e) {
        ArrayList<Tree24Node<E>> list = new ArrayList<>();
        Tree24Node<E> current = root;
        while (current != null) {
            list.add(current);
            if (matched(e, current)) {
                break;
            } else {
                current = getChildNode(e, current);
            }
        }
        return list;
    }


    private static class Tree24Node<E extends Comparable<E>> {
        ArrayList<E> elements = new ArrayList<>(3);
        ArrayList<Tree24Node<E>> child = new ArrayList<>(4);

        Tree24Node() {
        }

        Tree24Node(E e) {
            elements.add(e);
        }
    }
}
