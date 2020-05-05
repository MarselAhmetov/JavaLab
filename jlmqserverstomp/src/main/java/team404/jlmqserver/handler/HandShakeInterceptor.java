package team404.jlmqserver.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import team404.jlmqserver.TaskManager;

@Component
public class HandShakeInterceptor implements ChannelInterceptor {


    @Autowired
    private TaskManager manager;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        System.out.println(accessor.getCommand() + " to " + accessor.getDestination() + "\n");
        return message;
    }
}
