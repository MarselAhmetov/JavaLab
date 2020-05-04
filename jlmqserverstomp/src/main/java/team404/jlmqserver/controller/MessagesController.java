package team404.jlmqserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import team404.jlmqserver.service.interfaces.TaskService;

@Controller
public class MessagesController {

    @Autowired
    private SimpMessagingTemplate template;


    @Autowired
    private TaskService taskService;

    @MessageMapping("/produce/{queue}")
    public void receiveMessageFromProducer(Message<String> message, @DestinationVariable String queue) {
        System.out.println(message.toString());
        /*taskService.save(Task.builder()
                .queueName(message.getPayload().getQueueName())
                .taskId(message.getPayload().getMessageId())
                .status(TaskStatus.CREATED)
                .created(LocalDateTime.now())
                .build());*/
        template.convertAndSend("/jlmq/" + queue, message.getPayload());
    }

    @MessageMapping("/jlmq/{queue}")
    public void receiveMessageFromClient(Message<String> message, @DestinationVariable String queue) {
        System.out.println(message.toString());
    }
}
