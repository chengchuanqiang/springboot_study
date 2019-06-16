package com.ccq.springbootkafka.quartz.springquartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/5/29 17:34
 ***@Version 1.0.0
 ********************************/
public class SimpleJobListener implements JobListener {

    private static final String LISTENER_NAME = "QuartSchedulerListener";

    @Override
    public String getName() {
        return LISTENER_NAME;
    }

    /**
     * 任务被调度前
     *
     * @param context
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("jobToBeExecuted");
        System.out.println("Job : " + jobName + " is going to start...");
    }

    /**
     * 任务调度被拒了
     *
     * @param context
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
        //可以做一些日志记录原因
        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job : " + jobName + " is going to start...");
    }

    /**
     * 任务被调度后
     *
     * @param context
     * @param jobException
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("jobWasExecuted");

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job : " + jobName + " is finished...");

        if (jobException != null && !"".equals(jobException.getMessage())) {
            System.out.println("Exception thrown by: " + jobName + " Exception: " + jobException.getMessage());
        }
    }
}
