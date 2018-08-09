package com.ccq.springbootkafka.config.websocket;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {

    private static final String WEB_SOCKET_USER_ID = "WEB_SOCKET_USER_ID";

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            // todo 可获取session中信息
            HttpSession session = serverHttpRequest.getServletRequest().getSession();
            String userId = serverHttpRequest.getServletRequest().getParameter("userId");
            log.info("当前用户id:{}", userId);
            //todo 校验当前用户信息
            map.put(WEB_SOCKET_USER_ID, userId);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Exception e) {
        log.info("afterHandshake");
    }
}
