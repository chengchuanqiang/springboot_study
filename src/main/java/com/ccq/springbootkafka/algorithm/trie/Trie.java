package com.ccq.springbootkafka.algorithm.trie;

import java.util.HashMap;
import java.util.Map;

/********************************
 *** 字典树的实现
 ***@Author chengchuanqiang
 ***@Date 2018/7/24 19:35
 ***@Version 1.0.0
 ********************************/
public class Trie {

    private class Node {
        public boolean isWord;
        public Map<Character, Node> childMap;

        public Node(boolean isWord, Map<Character, Node> childMap) {
            this.isWord = isWord;
            this.childMap = childMap;
        }
    }

    private Node root;
    private int size;

    private void add(String word) {
        if (root == null) {
            root = new Node(false, new HashMap<>());
        }
        Node curr = root;
        for (Character c : word.toCharArray()) {
            if (curr.childMap.get(c) == null) {
                curr.childMap.put(c, new Node(false, new HashMap<>()));
            }
            curr = curr.childMap.get(c);
        }
        if (!curr.isWord) {
            curr.isWord = true;
            this.size++;
        }
    }

    private boolean contains(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) {
            if (curr.childMap.get(c) == null) {
                return false;
            }
            curr = curr.childMap.get(c);
        }
        return curr.isWord;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("chengchuanqiang");
        trie.add("cheng");
        trie.add("qiang");

        System.out.println(trie.contains("chengchuan"));
        System.out.println(trie.contains("chen"));
        System.out.println(trie.contains("cheng"));
        System.out.println(trie.contains("cxxx"));
        System.out.println(trie.contains("qiang"));

    }


}
