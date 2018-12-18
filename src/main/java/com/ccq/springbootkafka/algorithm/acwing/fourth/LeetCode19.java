package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/13 13:40
 ***@Version 1.0.0
 ********************************/
public class LeetCode19 {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 使用头指针！！！！
        ListNode h = new ListNode(0);
        h.next = head;

        ListNode curr = h;
        ListNode remove = h;
        while ((n--) > 0) {
            curr = curr.next;
        }

        while (null != curr.next) {
            curr = curr.next;
            remove = remove.next;
        }

        remove.next = remove.next.next;
        return h.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);

        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LeetCode19 test = new LeetCode19();
        head = test.removeNthFromEnd(head, 2);

        ListNode current = head;
        while (null != current) {
            System.out.print(current.val + "->");
            current = current.next;
        }
    }
}
