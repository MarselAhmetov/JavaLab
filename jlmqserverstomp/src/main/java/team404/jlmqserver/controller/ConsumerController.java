package team404.jlmqserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketHandler;
import protocol.Jlmq;
import protocol.JlmqConnector;
import protocol.JlmqConsumer;
import team404.jlmqserver.handler.CustomerStompSessionHandler;


@Controller
public class ConsumerController {

    @Autowired
    StompSessionHandlerAdapter customerStompSessionHandler;

    @GetMapping("/consumer")
    public ModelAndView getConsumerPage() {
        return new ModelAndView("consumer");
    }

    @PostMapping("/consumer")
    public ModelAndView createConsumer(@RequestParam("queueName") String queueName) {
        JlmqConnector connector = Jlmq.connector()
                .address("ws://localhost:8080/messages")
                .connect();
        JlmqConsumer consumer = connector.consumer()
                .subscribe(queueName)
                .onReceive(customerStompSessionHandler)
                .create();
        return new ModelAndView("redirect:/consumer");
    }
}
