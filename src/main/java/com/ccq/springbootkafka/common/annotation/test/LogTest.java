package com.ccq.springbootkafka.common.annotation.test;

import com.ccq.springbootkafka.common.annotation.MyLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 11:14
 ***@Version 1.0.0
 ********************************/
@Controller
public class LogTest {

    @MyLog
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "11111";
    }
}
