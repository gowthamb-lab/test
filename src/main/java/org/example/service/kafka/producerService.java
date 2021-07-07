package org.example.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.example.model.entity.Administrator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class producerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, Administrator> customerKafkaTemplate;

    @Value("${kafka.topic.string-demo.name}")
    private String STRING_TOPIC;

    @Value("${kafka.topic.json-demo.name}")
    private String JSON_TOPIC;

    public producerService(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Administrator> customerKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.customerKafkaTemplate = customerKafkaTemplate;
    }

    /**
     * To produce String data.
     * @param message
     *
     * */
    public void sendMessage(String message) {
        log.info(String.format("$$$$ => Producing message: %s", message));

        List<String> keyList = Arrays.asList("KEY1", "KEY2", "KEY3", "KEY4", "KEY5");
        //SendResult<String, String> value = this.kafkaTemplate.send(TOPIC, keyList.get(new Random().nextInt(keyList.size())), message).get();

        kafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, String>> future = t.send(STRING_TOPIC, keyList.get(new Random().nextInt(keyList.size())), message);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to produce message=[ {} ] due to : {}", message, ex.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
                }

            });
            return true;
        });
    }

    /**
     * To produce Customer data.
     * @param message
     *
     * */
    public void sendCustomerDataMessage(Administrator message) {
        log.info(String.format("$$$$ => Producing message: %s", message));

        customerKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, Administrator>> future = t.send(JSON_TOPIC, message.getAdminId(), message);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Administrator>>() {
                @Override
                public void onSuccess(SendResult<String, Administrator> result) {
                    log.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to produce message=[ {} ] due to : {}", message, ex.getMessage());
                }

            });
            return true;
        });
    }

}

