package com.ccq.springbootkafka.zookeeper.watcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/********************************
 *** 监听数据变化的watcher
 ***@Author chengchuanqiang
 ***@Date 2019/2/27 13:42
 ***@Version 1.0.0
 ********************************/
@Slf4j
public class DataWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("====================DataWatcher start====================");
        log.info("DataWatcher state: {}", watchedEvent.getState().name());
        log.info("DataWatcher type: {}", watchedEvent.getType());
        log.info("DataWatcher path: {}", watchedEvent.getPath());
        log.info("====================DataWatcher  end======================");
    }
}
