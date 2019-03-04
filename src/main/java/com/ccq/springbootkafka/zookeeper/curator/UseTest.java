package com.ccq.springbootkafka.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.poi.ss.formula.functions.T;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************
 *** curator使用测试
 ***@Author chengchuanqiang
 ***@Date 2019/3/1 16:23
 ***@Version 1.0.0
 ********************************/
public class UseTest {

    private final static String CONNECT = "127.0.0.1:2181";

    public static void main(String[] args) {

        CuratorFramework client = createClient3();
        client.start();

        try {
//            String res = client.create().forPath("/test1", "test1Data".getBytes());
//            System.out.println(res);

//            System.out.println(client.create().withMode(CreateMode.EPHEMERAL).forPath("/test2", "test2Data".getBytes()));
//            System.out.println(client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test/test3", "test3Data".getBytes()));
//
//            System.out.println(new String(client.getData().forPath("/test2"), StandardCharsets.UTF_8));
//            System.out.println(new String(client.getData().storingStatIn(new Stat()).forPath("/test/test3")));
//
//            System.out.println(client.checkExists().forPath("/test11"));
//
//            System.out.println(client.getChildren().forPath("/"));
//
//            // 事务
//            client.inTransaction().check().forPath("/test")
//                    .and()
//                    .create().withMode(CreateMode.EPHEMERAL).forPath("/test4", "test4Data".getBytes())
//                    .and()
//                    .setData().forPath("/test4", "data444".getBytes())
//                    .and()
//                    .commit();
//
//            System.out.println(client.getChildren().forPath("/"));

//            ExecutorService executor = Executors.newFixedThreadPool(2);
//            client.create()
//                    .creatingParentsIfNeeded()
//                    .withMode(CreateMode.EPHEMERAL)
//                    .inBackground((curatorFramework, curatorEvent) ->
//                                    System.out.println(String.format("eventType:%s, resultCode:%s", curatorEvent.getType(), curatorEvent.getResultCode())),
//                            executor)
//                    .forPath("/test5");

//            PathChildrenCache cache = new PathChildrenCache(client, "/pathCache", true);
//            cache.start();
//
//            PathChildrenCacheListener cacheListener = (client1, event) -> {
//                System.out.println("事件类型: " + event.getType());
//                if (null != event.getData()) {
//                    System.out.println("节点数据: " + event.getData().getPath() + "=" + new String(event.getData().getData()));
//                }
//            };
//
//            cache.getListenable().addListener(cacheListener);
//            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/pathCache/test01", "test01".getBytes());
//            Thread.sleep(10);
//            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/pathCache/test02", "test02".getBytes());
//            Thread.sleep(10);
//
//            client.setData().forPath("/pathCache/test01", "test01_v2".getBytes());
//            Thread.sleep(10);
//
//            for (ChildData childData : cache.getCurrentData()) {
//                System.out.println("getCurrentData" + childData.getPath() + " = " + new String(childData.getData()));
//            }
//
//            System.out.println(client.delete().forPath("/pathCache/test01"));
//            Thread.sleep(10);

            client.create().withMode(CreateMode.EPHEMERAL).forPath("/nodeCache", "nodeCacheData1".getBytes());
            NodeCache nodeCache = new NodeCache(client, "/nodeCache");
            NodeCacheListener listener = () -> {
                ChildData currentData = nodeCache.getCurrentData();
                if (currentData != null) {
                    System.out.println(currentData.getPath() +"节点数据:" + new String(currentData.getData()));
                } else {
                    System.out.println("节点被删除");
                }
            };
            nodeCache.getListenable().addListener(listener);
            nodeCache.start();

            client.setData().forPath("/nodeCache", "nodeCacheData2".getBytes());
            Thread.sleep(10);

            client.delete().forPath("/nodeCache");
            Thread.sleep(10);

            nodeCache.close();
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static CuratorFramework createClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(CONNECT, 5000, 5000, retryPolicy);
    }

    /**
     * 使用Fluent风格得api创建会话
     *
     * @return client
     */
    private static CuratorFramework createClient2() {

        return CuratorFrameworkFactory.builder()
                .connectString(CONNECT)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
    }

    private static CuratorFramework createClient3() {
        return CuratorFrameworkFactory.builder()
                .connectString(CONNECT)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("ccqTest")
                .build();
    }


}
