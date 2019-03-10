package com.ccq.springbootkafka.algorithm.lanqiaobei01;

import java.util.Scanner;

/********************************
 *** 4497: FBI树
 *** http://www.ncwu.club/problem.php?id=4497
 ***@Author chengchuanqiang
 ***@Date 2019/3/8 13:47
 ***@Version 1.0.0
 ********************************/
public class FbiTree {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            String str = input.next();
            Node root = new Node(str);
            buildTree(n, root);
            LRD(root);
            System.out.println();
        }
    }

    private static void buildTree(int n, Node node) {
        if (n == 0) {
            return;
        }
        String data = node.data;
        int len = data.length();
        Node l = new Node(data.substring(0, len / 2));
        Node r = new Node(data.substring(len / 2));
        node.left = l;
        node.right = r;
        buildTree(n - 1, l);
        buildTree(n - 1, r);

    }

    private static void LRD(Node node) {
        if (node == null) {
            return;
        }
        LRD(node.left);
        LRD(node.right);
        int num = 0;
        for (int i = 0; i < node.data.length(); i++) {
            if ('0' == node.data.charAt(i)) {
                num++;
            }
        }
        if (num == 0) {
            System.out.print("I");
        } else if (num == node.data.length()) {
            System.out.print("B");
        } else {
            System.out.print("F");
        }
    }
}

class Node {
    Node left;
    Node right;
    String data;

    public Node(String data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }
}


