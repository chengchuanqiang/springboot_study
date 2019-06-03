package com.ccq.springbootkafka.quartz.springquartz.service;

import com.ccq.springbootkafka.quartz.springquartz.job.SimpleJobListener;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/********************************
 *** 此处可以注入数据库操作，查询所有的任务配置
 ***@Author chengchuanqiang
 ***@Date 2019/5/29 17:30
 ***@Version 1.0.0
 ********************************/
@Component
public class SchedulerManager {

    private final SchedulerFactoryBean schedulerFactoryBean;

    private JobListener scheduleListener;

    @Autowired
    public SchedulerManager(SchedulerFactoryBean schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
    }

    /**
     * 开始定时任务
     *
     * @param corn
     * @param jobName
     * @param jobGroup
     * @param jobClass
     * @throws SchedulerException
     */
    public void startJob(String corn, String jobName, String jobGroup, Class<? extends Job> jobClass) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        if (null == scheduleListener) {
            scheduleListener = new SimpleJobListener();
            scheduler.getListenerManager().addJobListener(scheduleListener);
        }
        JobKey jobKey = new JobKey(jobName, jobGroup);
        if (!scheduler.checkExists(jobKey)) {
            scheduleJob(corn, scheduler, jobName, jobGroup, jobClass);
        }
    }


    public void deleteJob(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = new JobKey(jobName, jobGroup);
        scheduler.deleteJob(jobKey);
    }

    public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = new JobKey(jobName, jobGroup);
        scheduler.pauseJob(jobKey);
    }

    public void clearAll() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.clear();
    }

    /**
     * 动态创建job
     * 此处的任务可以配置
     *
     * @param corn
     * @param scheduler
     * @param jobName
     * @param jobGroup
     * @param jobClass
     */
    private void scheduleJob(String corn, Scheduler scheduler, String jobName, String jobGroup, Class<? extends Job> jobClass) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder).build();
        Date date = scheduler.scheduleJob(jobDetail, cronTrigger);
        System.out.println("启动成功" + date);
    }
}