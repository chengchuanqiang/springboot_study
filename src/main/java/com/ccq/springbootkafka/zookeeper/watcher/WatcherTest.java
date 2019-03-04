package com.ccq.springbootkafka.zookeeper.watcher;

import org.apache.zookeeper.ZooKeeper;

/********************************
 *** 测试类
 ***@Author chengchuanqiang
 ***@Date 2019/2/27 13:44
 ***@Version 1.0.0
 ********************************/
public class WatcherTest {

    private final static String CONNECT = "127.0.0.1:2181";

    public static void main(String[] args) {
        DefaultWatcher defaultWatcher = new DefaultWatcher();
        ChildrenWatcher childrenWatcher = new ChildrenWatcher();
        DataWatcher dataWatcher = new DataWatcher();

        try {
            ZooKeeper zooKeeper = new ZooKeeper(CONNECT, 100000, defaultWatcher);
            zooKeeper.getChildren("/GetChildren", childrenWatcher);
            zooKeeper.getData("/GetChildren", dataWatcher, null);
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
