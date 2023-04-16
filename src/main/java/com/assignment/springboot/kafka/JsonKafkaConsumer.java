package com.assignment.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.assignment.springboot.entity.UserEntity;
import com.assignment.springboot.service.UserService;

@Service
public class JsonKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @Autowired
    private UserService userService;

    public JsonKafkaConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "newtopic_json", groupId = "myGroup")
    public void consume(UserEntity user) {
        logger.info(String.format("Message received -> %s", user));
        userService.addUser(user);
    }
}
