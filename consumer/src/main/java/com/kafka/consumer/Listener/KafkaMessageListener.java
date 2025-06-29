package com.kafka.consumer.Listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "${kafka.topic.name}" , groupId =
    "${kafka.topic.groupId}")
    public void consume01(String message){
    log.info("consumer 01 online : {}",message);
    }

    @KafkaListener(topics = "${kafka.topic.name}" , groupId =
    "${kafka.topic.groupId}")
    public void consume02(String message){
    log.info("consumer 02 online : {}",message);
    }

    @KafkaListener(topics = "${kafka.topic.name}" , groupId =
    "${kafka.topic.groupId}")
    public void consume03(String message){
    log.info("consumer 03 online : {}",message);
    }

    // @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.topic.groupId}", concurrency = "3")
    // public void consume(String message) {
    //     log.info("🧵 [{}] Consumed: {}", Thread.currentThread().getName(), message);
    // }

}
