package com.ccq.springbootkafka.zookeeper.watcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/********************************
 *** 测试默认watcher
 ***@Author chengchuanqiang
 ***@Date 2019/2/27 10:17
 ***@Version 1.0.0
 ********************************/
@Slf4j
public class DefaultWatcher implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("====================DefaultWatcher start====================");
        log.info("DefaultWatcher state: {}", watchedEvent.getState().name());
        log.info("DefaultWatcher type: {}", watchedEvent.getType());
        log.info("DefaultWatcher path: {}", watchedEvent.getPath());
        log.info("====================DefaultWatcher  end======================");
    }
}
