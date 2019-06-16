package com.ccq.springbootkafka.quartz.springquartz.config;

import com.ccq.springbootkafka.quartz.springquartz.job.ScheduledJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/********************************
 *** 定时配置（可以配置静态定时任务）
 ***@Author chengchuanqiang
 ***@Date 2019/5/29 17:04
 ***@Version 1.0.0
 ********************************/
@Configuration
public class SchedulerConfig {

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, Trigger simpleTrigger) throws IOException {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setJobFactory(jobFactory);
//        factory.setQuartzProperties(quartzProperties());
//        factory.setTriggers(simpleTrigger);
//        return factory;
//    }
//
//    /**
//     * 静态方式配置定时任务
//     *
//     * @param jobDetail
//     * @return
//     */
//    @Bean
//    public CronTriggerFactoryBean simpleJobTrigger(@Qualifier("simpleJobDetail") JobDetail jobDetail) {
//
//        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
//        factoryBean.setJobDetail(jobDetail);
//        factoryBean.setStartDelay(1000L);
//        factoryBean.setName("trigger1");
//        factoryBean.setGroup("group1");
//
//        factoryBean.setCronExpression("0/2 * * * * ?");
//        return factoryBean;
//
//    }
//
//    @Bean
//    public JobDetailFactoryBean simpleJobDetail() {
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
//        factoryBean.setJobClass(ScheduledJob.class);
//        factoryBean.setGroup("group1");
//        factoryBean.setName("job1");
//        return factoryBean;
//    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
