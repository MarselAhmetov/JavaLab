package team404.jlmqserver.handler;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

@Component
public class CustomerStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Received task");
        System.out.println(payload.toString());
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Connected");
    }
}
