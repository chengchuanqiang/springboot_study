package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/16 9:46
 ***@Version 1.0.0
 ********************************/
public class LeetCode23 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode mergeKLists1(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        // 小顶堆
        PriorityQueue<Data> minHeap = new PriorityQueue<>(Comparator.comparingInt(d -> d.val));
        // 保存当前遍历到的节点
        ListNode[] curr = new ListNode[lists.length];

        // 初始化堆操作
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.add(new Data(lists[i].val, i));
                curr[i] = lists[i].next;
            }
        }

        if (minHeap.isEmpty()) {
            return null;
        }

        ListNode head = new ListNode(-1);
        ListNode res = null;
        Data deleteFromQueue;
        Integer currIndex;

        while (true) {
            deleteFromQueue = minHeap.poll();
            if (deleteFromQueue == null) {
                continue;
            }
            if (res == null) {
                res = new ListNode(deleteFromQueue.val);
                res.next = null;
                head.next = res;
            } else {
                res.next = new ListNode(deleteFromQueue.val);
                res = res.next;
            }
            currIndex = deleteFromQueue.nodeIndex;
            if (curr[currIndex] != null) {
                minHeap.add(new Data(curr[currIndex].val, currIndex));
                curr[currIndex] = curr[currIndex].next;
            } else {
                int j;
                for (j = 0; j < curr.length; j++) {
                    if (curr[j] != null) {
                        break;
                    }
                }
                if (j == curr.length) {
                    break;
                }

                minHeap.add(new Data(curr[j].val, j));
                curr[j] = curr[j].next;
            }

        }
        while (!minHeap.isEmpty()) {
            res.next = new ListNode(minHeap.poll().val);
            res = res.next;
        }
        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 小顶堆
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(d -> d.val));
        ListNode currNode;
        // 初始化堆操作
        for (int i = 0; i < lists.length; i++) {
            currNode = lists[i];
            while (currNode != null) {
                minHeap.add(new ListNode(currNode.val));
                currNode = currNode.next;
            }
        }
        ListNode head = new ListNode(-1);
        ListNode res = null;
        while (!minHeap.isEmpty()) {
            currNode = minHeap.poll();
            if (currNode == null) {
                continue;
            }
            if (res == null) {
                res = new ListNode(currNode.val);
                head.next = res;
            } else {
                res.next = new ListNode(currNode.val);
                res = res.next;
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = list1;
        listNodes[1] = list2;
        listNodes[2] = list3;

        LeetCode23 test = new LeetCode23();
        ListNode res = test.mergeKLists(listNodes);

        while (res != null) {
            System.out.print(res.val + "->");
            res = res.next;
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Data {
    public Integer val;
    public Integer nodeIndex; // 链表所在的索引

    public Data(Integer val, Integer nodeIndex) {
        this.val = val;
        this.nodeIndex = nodeIndex;
    }
}
