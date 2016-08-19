package com.lip.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebsocketEndPoint extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> users;//List会出现性能问题，最好用Map来存储，key用userid
    private static Logger logger = LoggerFactory.getLogger(WebsocketEndPoint.class);

    static {
        users = Collections.synchronizedMap(new HashMap<String, WebSocketSession>());
    }

    public WebsocketEndPoint() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 连接成功时候，会触发UI上onopen方法
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connect to the websocket success......");

        if (session.getAttributes().get("userId") != null) {
            String userId = session.getAttributes().get("userId").toString();
            users.put(userId, session);
        }

        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
        //TextMessage returnMessage = new TextMessage("你将收到的离线");
        //session.sendMessage(returnMessage);
    }

    /**
     * 在UI在用js调用websocket.send()时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
        session.sendMessage(returnMessage);
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (Map.Entry<String, WebSocketSession> entry : users.entrySet()) {
            WebSocketSession user = entry.getValue();
            if (user != null && user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (Map.Entry<String, WebSocketSession> entry : users.entrySet()) {
            WebSocketSession user = entry.getValue();
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}