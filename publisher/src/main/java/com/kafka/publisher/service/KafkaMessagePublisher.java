package com.kafka.publisher.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.kafka.publisher.config.KafkaTopicProperties;
import com.shared.dto.Customer;

@Service
public class KafkaMessagePublisher {

    private KafkaTemplate<String, Object> kafkaTemplate;
    private KafkaTopicProperties topicProperties;

    public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate,
            KafkaTopicProperties topicProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicProperties = topicProperties;
    }

    public void sendMessageToTopic(String message) {
        String topicName = topicProperties.getName();
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Message sent successfully: " + result.getRecordMetadata().toString());
            } else {
                System.err.println("Failed to send message: " + ex.getMessage());
            }
        });

    }

    public void sendEventsToTopic(Customer customer) {
        try {
            String topicName = topicProperties.getName();
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, customer);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Event sent successfully: " + result.getRecordMetadata().toString());
            } else {
                System.err.println("Failed to send message: " + ex.getMessage());
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
