package com.ccq.springbootkafka.util;

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/********************************
 *** 不带虚拟节点的一致性Hash算法
 ***@Author chengchuanqiang
 ***@Date 2018/7/17 10:19
 ***@Version 1.0.0
 ********************************/
public class ConsistentHashingWithoutVirtualNode {

    // 服务器ip:port列表
    private static List<String> servers = Arrays.asList("192.168.0.1:5555", "192.168.0.2:6666", "192.168.0.3:7777", "192.168.0.4:8888", "192.168.0.5:9999");

    // key代表hash值，value代表服务器
    private static SortedMap<Integer, String> sortedMap;

    static {
        sortedMap = new TreeMap<>();
        servers.forEach(str -> {
            sortedMap.put(HashUtil.getHash(str), str);
            System.out.println("[" + str + "] join in map, hashValue is [" + HashUtil.getHash(str) + "]");
        });
    }

    // 获取对应路由到服务器节点
    private static String getServer(String key) {
        int hash = HashUtil.getHash(key);
        // 得到大于等于hash的map值
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap.isEmpty()) {
            return sortedMap.get(sortedMap.firstKey());
        } else {
            return subMap.get(subMap.firstKey());
        }
    }

    private static void printSortMap() {
        System.out.println("\n========================sortMap=========================");
        sortedMap.forEach((k, v) -> System.out.println("[" + v + "]" + "[" + k + "]"));
        System.out.println("========================sortMap=========================\n");
    }

    public static void main(String[] args) {
        printSortMap();
        List<String> keys = Arrays.asList("测试0","测试1", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7", "测试8", "测试9");
        keys.forEach(key -> System.out.println("[" + key + "] hashValue is [" + HashUtil.getHash(key) + "], server is [" + getServer(key) + "]"));
    }
}



























