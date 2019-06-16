package com.ccq.springbootkafka.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/********************************
 *** Quartz 使用测试
 ***@Author chengchuanqiang
 ***@Date 2019/1/9 18:19
 ***@Version 1.0.0
 ********************************/
public class QuartzTest {

    public static class RAMJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println("Hello word " + simpleDateFormat.format(new Date()));
        }
    }

    public static void main(String[] args) {

        // 创建scheduler工厂
        StdSchedulerFactory factory = new StdSchedulerFactory();

        // 生产调度器实例
        try {
            Scheduler scheduler = factory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(RAMJob.class)
                    .withDescription("this is RAMJob")
                    .withIdentity("RAMJob", "RAMIobGroup")
                    .build();

            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withDescription("this is a scheduler")
                    .withIdentity("RAMTrigger", "RAMTriggerGroup")
                    .startAt(new Date())
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
//            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
