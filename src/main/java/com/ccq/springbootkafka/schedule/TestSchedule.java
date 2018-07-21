package com.ccq.springbootkafka.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/********************************
 *** 测试定时任务
 ***@Author chengchuanqiang
 ***@Date 2018/7/19 15:45
 ***@Version 1.0.0
 ********************************/
@Component
@Slf4j
public class TestSchedule {

    private static final String CORN_TIME = "0/10 * * * * ?";

    @Scheduled(cron = CORN_TIME)
    public void printTime(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(sdf.format(new Date()));
    }

}
