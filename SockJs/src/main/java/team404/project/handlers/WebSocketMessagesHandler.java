package team404.project.handlers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import team404.project.model.Authentication;
import team404.project.model.dto.JsonMessage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    @Autowired
    private Map<String, Map<String, WebSocketSession>> sessions;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    Authentication authentication;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {



        session.sendMessage(new TextMessage());





        if (!authentication.isAuthenticated()) {
            session.close();
        } else {
            String messageText = (String) message.getPayload();
            JsonMessage jsonMessage = objectMapper.readValue(messageText, JsonMessage.class);
            switch (jsonMessage.getHeader()) {
                case "ConnectToRoom":
                    LinkedHashMap connectInfo = (LinkedHashMap) jsonMessage.getPayload();
                    if (!sessions.containsKey(connectInfo.get("room"))) {
                        sessions.put((String) connectInfo.get("room"), new HashMap<>());
                    }
                    if (!sessions.get(connectInfo.get("room")).containsKey(connectInfo.get("username"))) {
                        sessions.get(connectInfo.get("room")).put((String) connectInfo.get("username"), session);
                    }
                    break;
                case "Message":
                    LinkedHashMap messageFromJson = (LinkedHashMap) jsonMessage.getPayload();
                    for (Map.Entry<String, WebSocketSession> connect : sessions.get(messageFromJson.get("room")).entrySet()) {
                        try {
                            connect.getValue().sendMessage(message);
                        } catch (Exception ex) {
                            synchronized (sessions.get(messageFromJson.get("room"))) {
                                sessions.get(messageFromJson.get("room")).remove(connect.getKey());
                            }
                        }
                    }
                    break;
                case "LeaveFromRoom":
                    LinkedHashMap leaveInfo = (LinkedHashMap) jsonMessage.getPayload();
                    sessions.get(leaveInfo.get("room")).remove(leaveInfo.get("username"));
                    break;
            }
        }


    }

}

