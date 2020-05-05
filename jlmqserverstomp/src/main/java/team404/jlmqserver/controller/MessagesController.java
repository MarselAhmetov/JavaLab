package team404.jlmqserver.controller;

import messages.TaskMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import team404.jlmqserver.TaskManager;
import team404.jlmqserver.model.Task;
import team404.jlmqserver.model.TaskStatus;
import team404.jlmqserver.service.interfaces.TaskService;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

@Controller
public class MessagesController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    Map<String, Queue<String>> queues;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskManager manager;



    @MessageMapping("/produce/{queue}")
    public void receiveMessageFromProducer(Message<String> message, @DestinationVariable String queue) {
        System.out.println("Creating task");
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        taskService.save(Task.builder()
                .queueName(queue)
                .taskId(accessor.getMessageId())
                .status(TaskStatus.CREATED)
                .created(LocalDateTime.now())
                .build());
        if (!queues.containsKey(queue)) {
            queues.put(queue, new ArrayDeque<>());
        }
        queues.get(queue).add(message.getPayload());
        manager.sendTask(queue);
    }

    @MessageMapping("/jlmq/queue/{queue}")
    public void receiveMessageFromClient(Message<String> message, @DestinationVariable String queue) {
        System.out.println(message.toString());
    }

    @MessageMapping("/jlmq/complete/{queue}")
    public void receiveMessageComplete(Message<String> message, @DestinationVariable String queue) {
        queues.get(queue).poll();
        manager.sendTask(queue);
        template.convertAndSend("/jlmq/complete/" + queue, message.getPayload());
    }
}
