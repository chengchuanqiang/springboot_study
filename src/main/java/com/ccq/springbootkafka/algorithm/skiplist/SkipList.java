package com.ccq.springbootkafka.algorithm.skiplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/********************************
 *** 跳越表实现，能够对递增链表实现logN的查询时间
 ***@Author chengchuanqiang
 ***@Date 2019/3/12 10:32
 ***@Version 1.0.0
 ********************************/
public class SkipList<T> {

    public static void main(String[] args) {
        SkipList<String> list = new SkipList<>();
        for (int i = 0; i < 5; i++) {
            list.put(i, i + "===");
            System.out.println(list);
        }
        list.delete(3.0);
        list.delete(3.5);
        System.out.println(list);
        System.out.println("查找4.0 ==>> " + list.get(4.0));
    }

    /**
     * 自定义最大深度
     */
    private static final int MAX_LEVEL = 1 << 6;
    /**
     * 跳越表第一层数据
     */
    private SkipNode<T> top;
    /**
     * 当前层级
     */
    private int level;
    private Random random = new Random();

    public SkipList() {
        this(4);
    }


    public SkipList(int level) {
        this.level = level;
        int i = level;
        SkipNode<T> temp = null;
        SkipNode<T> prev = null;
        while (i-- != 0) {
            temp = new SkipNode<>(null, Double.MIN_VALUE);
            temp.down = prev;
            prev = temp;
        }
        top = temp;
    }

    /**
     * 产生结点的高度，使用抛硬币的方式
     *
     * @return level
     */
    private int getRandomLevel() {
        int lev = 1;
        while (random.nextInt() % 2 == 0) {
            lev++;
        }
        return lev > MAX_LEVEL ? MAX_LEVEL : lev;
    }

    /**
     * 获取score分数得值
     *
     * @param score 分数
     * @return val
     */
    public T get(double score) {
        SkipNode<T> t = top;
        while (t != null) {
            if (t.score == score) {
                return t.data;
            }
            if (t.next == null) {
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else {
                    return null;
                }
            }
            if (t.next.score > score) {
                t = t.down;
            } else {
                t = t.next;
            }
        }
        return null;
    }

    /**
     * 添加分数为score的val
     *
     * @param score 分数
     * @param val   值
     */
    public void put(double score, T val) {
        SkipNode<T> t = top;
        SkipNode<T> curr = null;

        // 记录每一层当前结点的前驱结点
        List<SkipNode<T>> path = new ArrayList<>();
        while (t != null) {
            if (t.score == score) {
                curr = t;
                // 存在分数相同的值，更新val值
                break;
            }
            if (t.next == null) {
                // 需要向下查找，先记录该结点
                path.add(t);
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else {
                    break;
                }
            }
            if (t.next.score > score) {
                // 需要向下查找，先记录该结点
                path.add(t);
                if (t.down == null) {
                    break;
                }
                t = t.down;
            } else {
                t = t.next;
            }
        }
        if (curr != null) {
            // 当前分数重复，刷新val
            while (curr != null) {
                curr.data = val;
                curr = curr.down;
            }
        } else {
            // 当前表中不存在score值得结点，需要从下到上插入
            int lev = getRandomLevel();
            if (lev > level) {
                // 需要更新top这一列得结点数量，同时需要在path中增加这些新结点
                SkipNode<T> temp = null;
                SkipNode<T> prev = top;
                while (level++ != lev) {
                    temp = new SkipNode<>(null, Double.MIN_VALUE);
                    path.add(0, temp);
                    temp.down = prev;
                    prev = temp;
                }
                top = temp;
                level = lev;
            }

            //从后向前遍历path中的每一个结点，在其后面增加一个新的结点
            SkipNode<T> downTemp = null;
            SkipNode<T> temp;
            SkipNode<T> prev;
//            System.out.println("当前深度为" + level + ",当前path长度为" + path.size());
            for (int i = level - 1; i >= level - lev; i--) {
                temp = new SkipNode<>(val, score);
                prev = path.get(i);
                temp.next = prev.next;
                prev.next = temp;
                temp.down = downTemp;
                downTemp = temp;
            }
        }
    }

    /**
     * 删除分数的值
     *
     * @param score 分数
     */
    public void delete(double score) {
        SkipNode<T> t = top;
        while (t != null) {
            if (t.next == null) {
                t = t.down;
                continue;
            }
            if (t.next.score == score) {
                // 找到值进行删除
                t.next = t.next.next;
                // 删除当前结点后，还要继续寻找下一个进行删除
                t = t.down;
                continue;
            }
            if (t.next.score > score) {
                t = t.down;
            } else {
                t = t.next;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SkipNode<T> t = top;
        SkipNode<T> next;
        while (t != null) {
            next = t;
            while (next != null) {
                sb.append(next.score).append(" -> ");
                next = next.next;
            }
            sb.delete(sb.lastIndexOf("-"), sb.length()).append("\n");
            t = t.down;
        }
        return sb.toString();
    }


    /**
     * 跳越表的结点构成
     *
     * @param <E>
     */
    private static class SkipNode<E> {
        /**
         * 存储数据
         */
        E data;
        /**
         * 跳越表按照这个分值进行从小到大排序
         */
        double score;
        /**
         * next指针
         */
        SkipNode<E> next;
        /**
         * 指向下一层的结点
         */
        SkipNode<E> down;

        public SkipNode() {
        }

        public SkipNode(E data, double score) {
            this.data = data;
            this.score = score;
        }
    }
}
