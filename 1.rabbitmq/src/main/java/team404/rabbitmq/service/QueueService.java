package team404.rabbitmq.service;

import java.util.List;

public interface QueueService {
    void sendToQueue(String queue, String message);
    void sendToQueueExchange(String exchangeName, String exchangeType, String message, String route);
    void sendListToQueueExchange(String exchangeName, String exchangeType, List<String> messages, String route);
}
