package org.example.service.kafka;


import lombok.extern.slf4j.Slf4j;
import org.example.model.entity.Administrator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class consumerService {

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory",
            topics = "${kafka.topic.string-demo.name}",
            groupId = "${kafka.topic.string-demo.groupId}")
    public void consume(String message) {
        log.info(String.format("$$$$ => Consumed message: %s", message));
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.json-demo.name}",
            groupId = "${kafka.topic.json-demo.groupId}")
    public void consumeCustomerData(Administrator admin) {
        log.info("Consumed Message: {}, {}", admin.getAdminId(), admin.getUsername());
    }
}
