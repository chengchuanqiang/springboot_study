package com.ccq.springbootkafka.common.annotation.test;

import com.ccq.springbootkafka.common.annotation.MyAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 10:43
 ***@Version 1.0.0
 ********************************/
public class MyTest {

    @MyAnnotation(hello = "Hello, Beijing", world = "Hello World")
    public void output() {
        System.out.println("method output is running");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        // 获取要调用的类
        Class<MyTest> myTestClass = MyTest.class;
        Method method = myTestClass.getMethod("output");
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.hello());
            System.out.println(annotation.world());
        }
        System.out.println("-------------------------------");
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getName());
        }
    }
}
