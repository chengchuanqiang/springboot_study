package com.ccq.springbootkafka.quartz.springquartz.controller;

import com.ccq.springbootkafka.dto.enums.ResponseInfoType;
import com.ccq.springbootkafka.dto.response.BaseResponse;
import com.ccq.springbootkafka.quartz.springquartz.job.ScheduledJob;
import com.ccq.springbootkafka.quartz.springquartz.service.SchedulerManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/5/30 14:48
 ***@Version 1.0.0
 ********************************/
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    public SchedulerManager schedulerManager;

    @RequestMapping(value = "/job2", method = RequestMethod.GET)
    public BaseResponse<String> scheduleJob() {
        try {
            schedulerManager.startJob("0/5 * * * * ?", "job2", "group2", ScheduledJob.class);
            return BaseResponse.successInstance("定时任务启动成功!!!");

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return BaseResponse.failInstance(ResponseInfoType.ERROR4.getCode(), ResponseInfoType.ERROR4.getMsg());
    }

}
