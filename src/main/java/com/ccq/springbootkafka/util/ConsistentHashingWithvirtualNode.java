package com.ccq.springbootkafka.util;

import java.util.*;

/********************************
 *** 带虚拟节点的一致性Hash算法实现
 ***@Author chengchuanqiang
 ***@Date 2018/7/17 16:53
 ***@Version 1.0.0
 ********************************/
public class ConsistentHashingWithvirtualNode {
    // 服务器ip:port列表
    private static List<String> servers = Arrays.asList("192.168.0.1:5555", "192.168.0.2:6666", "192.168.0.3:7777", "192.168.0.4:8888", "192.168.0.5:9999");

    private static List<String> realNodes = new LinkedList<>();
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();
    private static String VIRTUAL_NODE_SPLIT = "#";

    // 虚拟节点个数
    private static final int VIRTUAL_NODES = 500;

    static{
        servers.forEach(str -> realNodes.add(str));

        realNodes.forEach(str -> {
            for(int i=0;i<VIRTUAL_NODES;i++){
                String virtualNodeName = str + VIRTUAL_NODE_SPLIT + i;
                int hash = HashUtil.getHash(virtualNodeName);
                virtualNodes.put(hash, virtualNodeName);
                System.out.println("[" + virtualNodeName + "] join in map, hashValue is [" + hash + "]");
            }
        });

    }

    private static String getServer(String key){

        String virtualNode;

        int hash = HashUtil.getHash(key);
        // 得到大于等于hash的map值
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        if (subMap.isEmpty()) {
            virtualNode = virtualNodes.get(virtualNodes.firstKey());
        } else {
            virtualNode = subMap.get(subMap.firstKey());
        }
        return virtualNode.split(VIRTUAL_NODE_SPLIT)[0];
    }

    private static void printSortMap() {
        System.out.println("\n========================sortMap=========================");
        virtualNodes.forEach((k, v) -> System.out.println("[" + v + "]" + "[" + k + "]"));
        System.out.println("========================sortMap=========================\n");
    }

    public static void main(String[] args) {
        printSortMap();
        List<String> keys = Arrays.asList("测试0","测试1", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7", "测试8", "测试9");
        keys.forEach(key -> System.out.println("[" + key + "] hashValue is [" + HashUtil.getHash(key) + "], server is [" + getServer(key) + "]"));
    }
}
