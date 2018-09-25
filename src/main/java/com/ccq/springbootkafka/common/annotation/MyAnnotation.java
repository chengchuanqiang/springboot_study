package com.ccq.springbootkafka.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 10:41
 ***@Version 1.0.0
 ********************************/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String hello() default "hello";

    String world();
}
