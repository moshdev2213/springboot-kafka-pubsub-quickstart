package com.kafka.publisher.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kafka.publisher.service.KafkaMessagePublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/publish")
public class PublisherController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            // publisher.sendMessageToTopic(message);
            for (int i = 1; i <= 10000; i++) {
                publisher.sendMessageToTopic(message + " - " + i);
            }
            return ResponseEntity.ok("Message Published Successfully ... ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
