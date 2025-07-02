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

    // @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.topic.groupId}")
    // public void consumeEvents(Customer customer) {
    //     log.info("Event consumer online : {}", customer.toString());
    // }

    // partition specific Listeners
    // @KafkaListener(
    //     topics = "${kafka.topic.name}", 
    //     groupId = "${kafka.topic.groupId}",
    //     topicPartitions = {@TopicPartition(topic = "${kafka.topic.name}",partitions = {"2"})}
    // )
    // public void consumeMsg(String customer) {
    //     log.info("Event consumer online : {}", customer.toString());
    // }

    // Retry Topic and DLT handler
    @RetryableTopic(
        attempts = "3", // Total attempts = 1 (initial) + 2 retries
        backoff = @Backoff(delay = 2000),
        dltTopicSuffix = "-dlt"
    )
    @KafkaListener(
        topics = "${kafka.topic.name}", 
        groupId = "${kafka.topic.groupId}"
        // topicPartitions = {@TopicPartition(
        //     topic = "${kafka.topic.name}",
        //     partitions = {"2"}
        // )}
    )
    public void consumeMsg(String customer) {
        log.info("Event consumer online : {}", customer.toString());
         // Simulate failure to test retry and DLT
        if (customer.toString().contains("fail")) {
            throw new RuntimeException("Failed to process message");
        }
    }

    @DltHandler
    public void handleDlt(ConsumerRecord<String,String> record){
        log.error("DLT message received: {}", record.value());
    }

    // ============== Method Based Listners =============
    // @KafkaListener(topics = "${kafka.topic.name}" , groupId =
    // "${kafka.topic.groupId}")
    // public void consume01(String message){
    // log.info("consumer 01 online : {}",message);
    // }

    // @KafkaListener(topics = "${kafka.topic.name}" , groupId =
    // "${kafka.topic.groupId}")
    // public void consume02(String message){
    // log.info("consumer 02 online : {}",message);
    // }

    // @KafkaListener(topics = "${kafka.topic.name}" , groupId =
    // "${kafka.topic.groupId}")
    // public void consume03(String message){
    // log.info("consumer 03 online : {}",message);
    // }

    // ============== Thread Based Listners =============
    // @KafkaListener(topics = "${kafka.topic.name}", groupId =
    // "${kafka.topic.groupId}", concurrency = "3")
    // public void consume(String message) {
    // log.info("🧵 [{}] Consumed: {}", Thread.currentThread().getName(), message);
    // }

}
