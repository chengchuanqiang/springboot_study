package com.ccq.springbootkafka.controller;


import com.ccq.springbootkafka.service.TestExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/********************************
 *** 测试异步 @Async 使用
 ***@Author chengchuanqiang
 ***@Date 2018/7/19 12:04
 ***@Version 1.0.0
 ********************************/
@RestController
@RequestMapping("executor")
public class TestExecutorController {

    @Autowired
    @Qualifier("testExecutorServiceWithoutReturnImpl")
    private TestExecutorService testExecutorService1;

    @Autowired
    @Qualifier("testExecutorServiceWithReturnImpl")
    private TestExecutorService testExecutorService2;

    /**
     * 测试没有返回值的异步调用
     * @return success
     */
    @RequestMapping(value = "test1",method =RequestMethod.GET)
    public String executorTest1() {
        try {
            testExecutorService1.executorTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 测试有返回值的异步调用
     * @return success
     */
    @RequestMapping(value = "test2",method =RequestMethod.GET)
    public String executorTest2() {
        try {
            testExecutorService2.executorTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

}
