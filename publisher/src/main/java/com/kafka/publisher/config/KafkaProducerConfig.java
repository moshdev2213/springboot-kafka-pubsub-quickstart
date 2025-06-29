package com.kafka.publisher.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    private final KafkaTopicProperties kafkaTopicProperties;

    public KafkaProducerConfig(KafkaTopicProperties topicProps) {
        this.kafkaTopicProperties = topicProps;
    }

    @Bean
    public NewTopic createNewTopic() {
        return new NewTopic(
            kafkaTopicProperties.getName(),
            kafkaTopicProperties.getPartitions(),
            kafkaTopicProperties.getReplicas()
        );
    }
}
