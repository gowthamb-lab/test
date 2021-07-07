package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.model.entity.Administrator;
import org.example.service.kafka.producerService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@Slf4j
@RequestMapping("/kafka")
public class kafkaController {

    private final producerService prodService;

    public kafkaController(producerService prodService) {
        this.prodService = prodService;
    }

    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam String message) {
        log.info("Message Received in KafkaController: {}",message);
        prodService.sendMessage(message);
        return "Message Received";
    }

    @PostMapping(value = "/publish/customer")
    public String sendMessageToKafkaTopic(@RequestBody Administrator customer) {
        log.info("Message Received in KafkaController: {}",customer);
        String order = customer.getAdminId();
        customer.setAdminId(order.concat(Long.toString(Instant.now().getEpochSecond())));
        prodService.sendCustomerDataMessage(customer);
        return "Message Received";
    }
}