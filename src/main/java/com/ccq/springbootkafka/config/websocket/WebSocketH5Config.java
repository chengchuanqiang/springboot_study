package com.ccq.springbootkafka.config.websocket;

import com.ccq.springbootkafka.websocket.MyHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/********************************
 *** 实现接口来配置websocket请求的路径和拦截器
 ***@Author chengchuanqiang
 ***@Date 2018/7/31 14:07
 ***@Version 1.0.0
 ********************************/
@Configuration
@EnableWebSocket
public class WebSocketH5Config implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
       // handler是websocket的核心，配置入口
        registry.addHandler(new MyHandler(), "/myHandler/{ID}").setAllowedOrigins("*").addInterceptors(new WebSocketInterceptor());
    }
}
