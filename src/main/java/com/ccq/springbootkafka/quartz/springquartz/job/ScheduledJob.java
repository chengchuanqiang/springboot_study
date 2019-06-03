package com.ccq.springbootkafka.quartz.springquartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/********************************
 *** 自定义定时任务
 ***@Author chengchuanqiang
 ***@Date 2019/5/29 17:25
 ***@Version 1.0.0
 ********************************/
@Slf4j
public class ScheduledJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDetail jobDetail = context.getJobDetail();
        JobKey key = jobDetail.getKey();
        System.out.println(key);
        // 任务执行逻辑
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("spring-quartz " + simpleDateFormat.format(new Date()));
    }
}
