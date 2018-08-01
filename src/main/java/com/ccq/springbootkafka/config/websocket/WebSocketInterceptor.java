package com.ccq.springbootkafka.config.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/31 14:12
 ***@Version 1.0.0
 ********************************/
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        if (request instanceof ServletServerHttpRequest) {
            String ID = request.getURI().toString().split("ID=")[1];
            System.out.println("当前session的ID="+ID);
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            HttpSession session = serverHttpRequest.getServletRequest().getSession();
            map.put("WEBSOCKET_USERID",ID);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("进来webSocket的afterHandshake拦截器！");
    }
}
