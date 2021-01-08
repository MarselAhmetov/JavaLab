package team404.rabbitmq.consumer.exchange.fanout;

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

public class OfferCreator {

    private final static String EXCHANGE_NAME = "documents";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String TYPE = "Offer";


    public static void main(String[] args) {
        HtmlToPdfParser parser = new HtmlToPdfParser();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");

            ObjectMapper objectMapper = new ObjectMapper();

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                DocumentDto documentDto = objectMapper.readValue(message.getBody(), DocumentDto.class);
                try {

                    Context context = new Context();
                    context.setVariable("firstName", documentDto.getFirstName());
                    context.setVariable("lastName", documentDto.getLastName());
                    parser.createPdfFromTemplate("/templates/" + TYPE, context, "documents/" + documentDto.getLastName() + "_" + TYPE + ".pdf");
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

