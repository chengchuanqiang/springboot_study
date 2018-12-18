package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/13 13:55
 ***@Version 1.0.0
 ********************************/
public class LeetCode83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode curr = head;
        ListNode next = head.next;
        int val = head.val;

        while (null != next) {
            if (next.val == val) {
                next = next.next;
                curr.next = next;
            } else {
                curr = next;
                next = next.next;
                val = curr.val;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);

        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);

        LeetCode83 test = new LeetCode83();
        head = test.deleteDuplicates(head);

        ListNode current = head;
        while (null != current) {
            System.out.print(current.val + "->");
            current = current.next;
        }
    }
}
