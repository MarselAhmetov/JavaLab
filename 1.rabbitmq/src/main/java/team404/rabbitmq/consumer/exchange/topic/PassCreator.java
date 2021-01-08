package team404.rabbitmq.consumer.exchange.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.thymeleaf.context.Context;
import team404.rabbitmq.consumer.exchange.HtmlToPdfParser;
import team404.rabbitmq.domain.DocumentDto;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PassCreator {

    private final static String PASS_ROUTING_KEY = "doc.pass";
    private final static String DOC_EXCHANGE = "doc_topic_exchange";


    public static void main(String[] args) {
        HtmlToPdfParser parser = new HtmlToPdfParser();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(DOC_EXCHANGE, "topic");
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, DOC_EXCHANGE, PASS_ROUTING_KEY);

            ObjectMapper objectMapper = new ObjectMapper();

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                DocumentDto documentDto = objectMapper.readValue(message.getBody(), DocumentDto.class);
                try {
                    Context context = new Context();
                    context.setVariable("firstName", documentDto.getFirstName());
                    context.setVariable("lastName", documentDto.getLastName());
                    context.setVariable("age", documentDto.getAge());
                    parser.createPdfFromTemplate("/templates/Pass", context, "documents/" + documentDto.getLastName() + "_" + "pass" + ".pdf");
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (DocumentException e) {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                    System.err.println("Error");
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

