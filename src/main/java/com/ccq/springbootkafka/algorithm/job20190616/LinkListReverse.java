package com.ccq.springbootkafka.algorithm.job20190616;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/6/25 8:14
 */
public class LinkListReverse {

    public static void print(Node node) {
        if (node == null) {
            System.out.println(node);
        } else {
            StringBuffer sbf = new StringBuffer();
            Node curr = node;
            while (null != curr) {
                sbf.append(curr.value + "->");
                curr = curr.next;
            }
            System.out.println(sbf.substring(0, sbf.length() - 2));
        }
    }

    private static Node reverse(Node node) {
        if (null == node || null == node.next) {
            return node;
        }

        Node curr = node;
        Node pre = null;
        while (null != curr) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        print(node1);

        Node reverse = reverse(node1);
        print(reverse);
    }

}

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}
