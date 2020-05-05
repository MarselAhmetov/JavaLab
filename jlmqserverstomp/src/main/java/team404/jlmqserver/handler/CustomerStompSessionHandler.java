package team404.jlmqserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import team404.jlmqserver.model.Message;

@Component
public class CustomerStompSessionHandler extends StompSessionHandlerAdapter {

    StompSession session;
    @Autowired
    ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Received task");
        System.out.println(payload.toString());
        StompHeaders stompHeaders = new StompHeaders();
        stompHeaders.add("destination", "/app/jlmq/complete" + headers.getDestination().substring(headers.getDestination().lastIndexOf("/")));
        stompHeaders.add("type", "complete");
        session.send(stompHeaders, payload);
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Connected");
        this.session = session;
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        throw new RuntimeException("Failure in WebSocket handling", exception);
    }
}
