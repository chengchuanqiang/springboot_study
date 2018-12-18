package com.ccq.springbootkafka.algorithm.tree;

public interface BinaryTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * 查询数值
     *
     * @param e 数值
     * @return true|false
     */
    boolean search(E e);

    /**
     * 插入数值
     *
     * @param e 数值
     * @return true|false
     */
    boolean insert(E e);

    /**
     * 删除数值
     *
     * @param e 数值
     * @return true|false
     */
    boolean delete(E e);

    /**
     * 先序遍历
     */
    void inorder();

    /**
     * 中序遍历
     */
    void preorder();

    /**
     * 后序遍历
     */
    void postorder();

    /**
     * 获取树节点个数
     *
     * @return int
     */
    int getSize();

    /**
     * 判断树是否为空
     *
     * @return true|false
     */
    boolean isEmpty();

    /**
     * 判断树是否为二叉搜索树
     *
     * @return true|false
     */
    boolean isBST();

    TreeNode<E> getRoot();

}
