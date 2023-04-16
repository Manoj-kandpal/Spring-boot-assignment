package com.assignment.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.assignment.springboot.entity.UserEntity;

@Service
public class JsonKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, UserEntity> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, UserEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserEntity data) {
        Message<UserEntity> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, "newtopic_json").build();

        kafkaTemplate.send(message);
        logger.info(String.format("Message sent -> %s", data));
    }
}
