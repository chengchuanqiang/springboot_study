package com.ccq.springbootkafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/********************************
 *** 线程池自定义设置
 ***@Author chengchuanqiang
 ***@Date 2018/7/19 11:41
 ***@Version 1.0.0
 ********************************/
@Configuration
@EnableAsync
public class ExecutorConfiguration {

    @Bean("testExecutor1")
    public Executor getTestExecutor1(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        taskExecutor.setCorePoolSize(30);
        // 最大线程池大小
        taskExecutor.setMaxPoolSize(40);
        // 任务队列大小
        taskExecutor.setQueueCapacity(30);
        // 最大存活时间
        taskExecutor.setKeepAliveSeconds(60);
        // 拒绝策略, 抛弃, 不执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        // 线程名称
        taskExecutor.setThreadNamePrefix("testExecutor1---------");
        return taskExecutor;
    }

    @Bean("testExecutor2")
    public Executor getTestExecutor2(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        taskExecutor.setCorePoolSize(30);
        // 最大线程池大小
        taskExecutor.setMaxPoolSize(50);
        // 任务队列大小
        taskExecutor.setQueueCapacity(30);
        // 最大存活时间
        taskExecutor.setKeepAliveSeconds(60);
        // 拒绝策略, 抛弃, 不执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        // 线程名称
        taskExecutor.setThreadNamePrefix("testExecutor2---------");
        return taskExecutor;
    }

}
