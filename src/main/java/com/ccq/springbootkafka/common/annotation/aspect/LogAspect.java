package com.ccq.springbootkafka.common.annotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/********************************
 *** 日志切面实现
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 11:01
 ***@Version 1.0.0
 ********************************/
@Component
@Aspect
public class LogAspect {
    @Pointcut("@annotation(com.ccq.springbootkafka.common.annotation.MyLog)")
    private void cut() {
    }

    @Around("cut()")
    public void advice(ProceedingJoinPoint joinPoint) {
        System.out.println("环绕通知开始");

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("环绕通知结束");
    }

    @Before("cut()")
    public void before() {
        System.out.println("before");
    }

    @After("cut()")
    public void after() {
        System.out.println("after");
    }

}
