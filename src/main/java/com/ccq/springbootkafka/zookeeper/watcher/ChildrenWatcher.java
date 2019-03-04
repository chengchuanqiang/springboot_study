package com.ccq.springbootkafka.zookeeper.watcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/********************************
 *** 用于监听子节点变化的watcher
 ***@Author chengchuanqiang
 ***@Date 2019/2/27 13:40
 ***@Version 1.0.0
 ********************************/
@Slf4j
public class ChildrenWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("====================ChildrenWatcher start====================");
        log.info("ChildrenWatcher state: {}", watchedEvent.getState().name());
        log.info("ChildrenWatcher type: {}", watchedEvent.getType());
        log.info("ChildrenWatcher path: {}", watchedEvent.getPath());
        log.info("====================ChildrenWatcher  end======================");
    }
}
