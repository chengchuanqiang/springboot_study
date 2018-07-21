package com.ccq.springbootkafka.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/********************************
 *** Async使用
 ***@Author chengchuanqiang
 ***@Date 2018/7/19 14:48
 ***@Version 1.0.0
 ********************************/

@Component
@Slf4j
public class AsyncTask {

    @Async("testExecutor1")
    public void task1() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task1任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }

    @Async("testExecutor2")
    public void task2() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task2任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }

    @Async
    public void task3() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task3任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }

    @Async("testExecutor1")
    public Future<String> task4() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task4任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task4执行完毕");
    }

    @Async("testExecutor2")
    public Future<String> task5() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task5任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task5执行完毕");
    }

    @Async
    public Future<String> task6() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task6任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task6执行完毕");
    }

}
