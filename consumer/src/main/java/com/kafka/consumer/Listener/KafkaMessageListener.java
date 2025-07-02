package com.kafka.consumer.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.shared.dto.Customer;


@Service
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    // ============== Thread Based Listners =============
    @KafkaListener(topics = "${kafka.topic.name}", groupId =
    "${kafka.topic.groupId}", concurrency = "3")
    public void consume(String message) {
    log.info("🧵 [{}] Consumed: {}", Thread.currentThread().getName(), message);
    }

}
