package com.user_service.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @KafkaListener(topics = "user-created", groupId = "group1")
    public void consume(String message) {
        System.out.println("Received: " + message);
    }
}