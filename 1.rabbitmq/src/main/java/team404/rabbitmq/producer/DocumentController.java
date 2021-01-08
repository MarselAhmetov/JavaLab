package team404.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.rabbitmq.domain.DocumentDto;
import team404.rabbitmq.service.QueueService;

@RestController
public class DocumentController {

    @Autowired
    private QueueService queueService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/prepareIntroDocuments")
    public ResponseEntity<String> prepareDocuments(@RequestBody DocumentDto document) {
        String message;
        try {
            message = objectMapper.writeValueAsString(document);
            queueService.sendToQueueExchange("documents", "fanout", message, "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/prepareBasicDocument/{type}")
    public ResponseEntity<String> preparePass(@RequestBody DocumentDto document, @PathVariable String type) {
        String message;
        try {
            message = objectMapper.writeValueAsString(document);
            queueService.sendToQueueExchange("doc_topic_exchange", "topic", message, "doc." + type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Ok");
    }

}
