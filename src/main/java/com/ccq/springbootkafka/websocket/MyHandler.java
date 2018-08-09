package com.ccq.springbootkafka.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/********************************
 *** webSocket
 ***@Author chengchuanqiang
 ***@Date 2018/7/31 14:15
 ***@Version 1.0.0
 ********************************/
@Slf4j
public class MyHandler implements WebSocketHandler {

    //在线用户列表
    private static final Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

    private static final String WEB_SOCKET_USER_ID = "WEB_SOCKET_USER_ID";

    /**
     * 客户端连接回调
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getClientId(session);
        log.info("{}, 成功建立连接" + userId);
        if (userId != null) {
            users.put(userId, session);
            session.sendMessage(new TextMessage("用户 " + userId + "成功建立socket连接"));
            log.info("userId : {}, session : {}", userId, session);
        }
        log.info("当前在线人数 : {}", users.size());
    }

    /**
     * 客户端发消息回调
     * @param session
     * @param webSocketMessage
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception{
        try {
            JSONObject jsonobject = JSONObject.parseObject((String) webSocketMessage.getPayload());
            String clientId = jsonobject.getString("id");
            String message = jsonobject.getString("message");
            String userId = getClientId(session);
            log.info("{} 发送给 {} 消息 : {}", userId, clientId, message);
            sendMessageToUser(clientId, new TextMessage("服务器转发消息 : " + message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端发生异常回调
     * @param session
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        String userId = getClientId(session);
        log.info("{}, 连接错误", userId);
        users.remove(userId);
    }

    /**
     * 客户端关闭连接回调
     * @param session
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = getClientId(session);
        log.info("{}, 连接已关闭, status : ", userId, status);
        users.remove(userId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     *
     * @param session
     * @return
     */
    private String getClientId(WebSocketSession session) {
        try {
            return (String) session.getAttributes().get(WEB_SOCKET_USER_ID);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 发送信息给指定用户
     *
     * @param clientId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (users.get(clientId) == null) {
            return false;
        }
        WebSocketSession session = users.get(clientId);
        if (!session.isOpen()) {
            return false;
        }
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
