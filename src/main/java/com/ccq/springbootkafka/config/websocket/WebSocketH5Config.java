package com.ccq.springbootkafka.config.websocket;

import com.ccq.springbootkafka.websocket.MyHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/********************************
 *** 实现接口来配置webSocket请求的路径和拦截器
 ***@Author chengchuanqiang
 ***@Date 2018/7/31 14:07
 ***@Version 1.0.0
 ********************************/
@Configuration
@EnableWebSocket
public class WebSocketH5Config implements WebSocketConfigurer {

    private static final String WEB_SOCKET_URL = "/connect/webSocket";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // handler是webSocket的核心，配置入口
        registry.addHandler(new MyHandler(), WEB_SOCKET_URL).addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("*");
    }
}
