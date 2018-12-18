package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.Stack;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/11/18 17:59
 */
public class LeetCode25 {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        LeetCode25 test = new LeetCode25();

        ListNode res = test.reverseKGroup(head1, 2);
        while (res != null) {
            System.out.print(res.val + "->");
            res = res.next;
        }
        System.out.println();

        res = test.reverseKGroup(head1, 3);
        while (res != null) {
            System.out.print(res.val + "->");
            res = res.next;
        }
        System.out.println();
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        return null;
    }
}
