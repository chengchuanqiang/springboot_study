package com.ccq.springbootkafka.service.impl;

import com.ccq.springbootkafka.task.AsyncTask;
import com.ccq.springbootkafka.service.TestExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/********************************
 *** 没有返回值的异步调用
 ***@Author chengchuanqiang
 ***@Date 2018/7/19 14:06
 ***@Version 1.0.0
 ********************************/
@Slf4j
@Service
public class TestExecutorServiceWithoutReturnImpl implements TestExecutorService {

    @Autowired
    private AsyncTask asyncTask;

    @Override
    public void executorTest() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        asyncTask.task1();
        asyncTask.task2();
        asyncTask.task3();
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }
}
