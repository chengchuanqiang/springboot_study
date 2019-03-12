package com.ccq.springbootkafka.zookeeper.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/3/1 18:40
 ***@Version 1.0.0
 ********************************/
public class BaseDistributedLock {
    /**
     * zk客户端
     */
    private final ZkClient zkClient;
    /**
     * 用于保存zookeeper中实现分布式锁的节点，该节点是持久节点，在该节点下面创建临时顺序节点来实现分布式锁
     */
    private final String basePath;
    private final String path;

    /**
     * 锁名称前缀
     */
    private final String lockName;

    /**
     * 最大重试次数
     */
    private static final Integer MAX_RETRY_COUNT = 10;


    public BaseDistributedLock(ZkClient zkClient, String path, String lockName) {
        this.zkClient = zkClient;
        this.basePath = path;
        this.path = path.concat("/").concat(lockName);
        this.lockName = lockName;
    }

    /**
     * 删除节点
     *
     * @param path 节点路径
     * @throws Exception 异常
     */
    private void deletePath(String path) throws Exception {
        if (null != path) {
            zkClient.delete(path);
            System.out.println(path + "锁已释放...");
        }
    }

    /**
     * 创建临时顺序节点
     *
     * @param client 客户端
     * @param path   节点路径
     * @return 创建情况
     * @throws Exception 异常
     */
    private String createEphemeralSequential(ZkClient client, String path) throws Exception {
        return client.createEphemeralSequential(path, null);
    }

    /**
     * 获取parentPath节点下的所有顺序节点，并且从小到大排序
     *
     * @return list
     */
    private List<String> getSortedChildren() {
        try {
            List<String> childrenList = zkClient.getChildren(basePath);
            childrenList = childrenList.stream().sorted(Comparator.comparing(s -> getLockNodeNumber(s, lockName))).collect(Collectors.toList());
            return childrenList;
        } catch (ZkNoNodeException e) {
            zkClient.createPersistent(basePath, true);
            return getSortedChildren();
        }
    }

    /**
     * 获锁节点的序号
     *
     * @param str      字符串
     * @param lockName 锁名称前缀
     * @return number
     */
    private String getLockNodeNumber(String str, String lockName) {
        int index = str.lastIndexOf(lockName);
        if (index > 0) {
            index += lockName.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }

    /**
     * 获取锁核心方法
     *
     * @param startMillis  当前系统时间
     * @param millisToWait 超时时间
     * @param path         路径
     * @return true|false
     * @throws Exception 异常
     */
    private boolean waitToLock(long startMillis, Long millisToWait, String path) throws Exception {
        /**
         * 获取锁标志
         */
        boolean haveTheLock = false;
        /**
         * 删除锁标志
         */
        boolean doDelete = false;

        try {

            while (!haveTheLock) {
                List<String> childrenList = getSortedChildren();
                String seqNodeName = path.substring(basePath.length() + 1);

                int ourIndex = childrenList.indexOf(seqNodeName);

                if (ourIndex < 0) {
                    throw new ZkNoNodeException("Node is not find: " + seqNodeName);
                }

                boolean isGetLock = (ourIndex == 0);
                // 如何判断其他客户端是否释放了锁，从子节点列表中获取到比自己小得那个节点，并对其建立监听
                String pathToWatch = isGetLock ? null : childrenList.get(ourIndex - 1);
                if (isGetLock) {
                    haveTheLock = true;
                } else {
                    String preSeqPath = basePath.concat("/").concat(pathToWatch);
                    // 如果次小的节点被删除了，则表示当前客户端的节点应该是最小的，所以使用CountDownLatch来实现等待
                    final CountDownLatch latch = new CountDownLatch(1);
                    final IZkDataListener preListener = new IZkDataListener() {

                        /**
                         * 监听指定节点的数据发生变化触发该方法
                         *
                         * @param dataPath 节点
                         * @param data     数据
                         * @throws Exception 异常
                         */
                        @Override
                        public void handleDataChange(String dataPath, Object data) throws Exception {

                        }

                        /**
                         * 监听指定节点删除触发该方法
                         *
                         * @param dataPath 节点
                         * @throws Exception 异常
                         */
                        @Override
                        public void handleDataDeleted(String dataPath) throws Exception {
                            latch.countDown();
                        }
                    };

                    try {

                        // 监听比自己小的那个节点
                        zkClient.subscribeDataChanges(preSeqPath, preListener);

                        // 发生超时需要删除节点
                        if (null != millisToWait) {
                            millisToWait = millisToWait - (System.currentTimeMillis() - startMillis);
                            startMillis = System.currentTimeMillis();
                            if (millisToWait <= 0) {
                                doDelete = true;
                                break;
                            }

                            latch.await(millisToWait, TimeUnit.MILLISECONDS);
                        } else {
                            latch.await();
                        }
                    } catch (InterruptedException ignored) {
                        //ignored
                    } finally {
                        zkClient.unsubscribeDataChanges(preSeqPath, preListener);
                    }
                }

            }
        } catch (Exception e) {
            // 发生异常需要删除的节点
            doDelete = true;
            throw e;
        } finally {
            // 删除节点
            if (doDelete) {
                deletePath(path);
            }
        }
        return haveTheLock;
    }

    /**
     * 释放锁
     *
     * @param lockPath 节点
     * @throws Exception 异常
     */
    protected void releaseLock(String lockPath) throws Exception {
        deletePath(lockPath);
    }

    /**
     * 尝试获取锁
     *
     * @param time     超时时间
     * @param timeUnit 时间单位格式
     * @return 获取成功返回路径，失败返回null
     * @throws Exception 异常
     */
    protected String attemptLock(Long time, TimeUnit timeUnit) throws Exception {

        final long startMillis = System.currentTimeMillis();
        final Long millisToWaits = (null != timeUnit) ? timeUnit.toMillis(time) : null;

        String ourPath = null;
        // 获取锁标志
        boolean hasTheLock = false;
        // 是否完成得到锁
        boolean isDone = false;
        // 重试次数
        int retryCount = 0;

        while (!isDone) {
            isDone = true;
            try {
                // 在locker下创建客户端要获取锁的[临时]顺序节点
                ourPath = createEphemeralSequential(zkClient, path);

                // 用户判断用户是否获取到了锁，即自己建的顺序节点在locker的所有子节点中是否最小
                // 如果没有获取到锁，则等待其他客户端锁的释放，并且稍后重试直到获取锁超时
                hasTheLock = waitToLock(startMillis, millisToWaits, ourPath);
            } catch (ZkNoNodeException e) {
                if (retryCount++ < MAX_RETRY_COUNT) {
                    isDone = false;
                } else {
                    throw e;
                }
            }
        }

        System.out.println(ourPath + "锁获取" + (hasTheLock ? "成功" : "失败"));
        if (hasTheLock) {
            return ourPath;
        }
        return null;
    }

}
