package com.assignment.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.springboot.entity.UserEntity;
import com.assignment.springboot.kafka.JsonKafkaProducer;
import com.assignment.springboot.kafka.StringKafkaProducer;
import com.assignment.springboot.service.AzureKeyVaultService;
import com.assignment.springboot.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringKafkaProducer stringKafkaProducer;

    @Autowired
    private JsonKafkaProducer jsonKafkaProducer;

    @Autowired
    private AzureKeyVaultService azureKeyVaultService;

//    public UserController(UserService userService, StringKafkaProducer stringKafkaProducer) {
//        this.userService = userService;
//        this.stringKafkaProducer = stringKafkaProducer;
//    }

    public UserController(UserService userService, StringKafkaProducer stringKafkaProducer, JsonKafkaProducer jsonKafkaProducer) {
        this.userService = userService;
        this.stringKafkaProducer = stringKafkaProducer;
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

//    public UserController(JsonKafkaProducer jsonKafkaProducer) {
//        this.jsonKafkaProducer = jsonKafkaProducer;
//    }

    @GetMapping("/get-users")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/user/send-kafka-message?message=hello world
    @GetMapping("/send-kafka-message")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        stringKafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message Sent to Producer");
    }

    @PostMapping("/send-kafka-message")
    public ResponseEntity<String> publishJson(@RequestBody UserEntity user) {
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Message Sent to Producer Successfully");
    }

    @GetMapping("/get-secret")
    public String getSecretFromAzure() {
        return azureKeyVaultService.getSecret();
    }
}
