package com.ccq.springbootkafka.websocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/31 14:15
 ***@Version 1.0.0
 ********************************/
public class MyHandler implements WebSocketHandler {

    //在线用户列表
    private static final Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("成功建立连接");
        String ID = session.getUri().toString().split("ID=")[1];
        System.out.println(ID);
        if (ID != null) {
            users.put(ID, session);
            session.sendMessage(new TextMessage("用户 " + ID + "成功建立socket连接"));
            System.out.println(ID);
            System.out.println(session);
        }
        System.out.println("当前在线人数：" + users.size());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        try {
            JSONObject jsonobject = JSONObject.parseObject((String) webSocketMessage.getPayload());
            System.out.println(jsonobject.get("id"));
            System.out.println(jsonobject.get("message") + ":来自" + (String) webSocketSession.getAttributes().get("WEBSOCKET_USERID") + "的消息");
            sendMessageToUser(jsonobject.get("id") + "", new TextMessage("服务器收到了，" + jsonobject.get("message")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.out.println("连接出错");
        users.remove(getClientId(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("连接已关闭：" + status);
        users.remove(getClientId(session));
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
    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get("WEBSOCKET_USERID");
            return clientId;
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
        if (users.get(clientId) == null) return false;
        WebSocketSession session = users.get(clientId);
        System.out.println("sendMessage:" + session);
        if (!session.isOpen()) return false;
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
