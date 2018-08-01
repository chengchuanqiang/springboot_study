package com.ccq.springbootkafka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/31 14:27
 ***@Version 1.0.0
 ********************************/
@Controller
public class WebSocketController {

    @RequestMapping("webSocket")
    public String index(){
        return "webSocket";
    }
}
