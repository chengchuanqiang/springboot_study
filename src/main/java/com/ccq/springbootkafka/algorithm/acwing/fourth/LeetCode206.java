package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/13 14:04
 ***@Version 1.0.0
 ********************************/
public class LeetCode206 {
    public ListNode reverseList(ListNode head) {

        if (null == head) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        ListNode next = head.next;
        while (null != next) {
            ListNode temp = next.next;

            curr.next = pre;
            next.next = curr;
            pre = curr;
            curr = next;
            next = temp;
        }

        return curr;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);

        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LeetCode206 test = new LeetCode206();
        head = test.reverseList(head);

        ListNode current = head;
        while (null != current) {
            System.out.print(current.val + "->");
            current = current.next;
        }
    }
}
