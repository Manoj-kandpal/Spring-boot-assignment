package com.assignment.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.springboot.entity.UserEntity;
import com.assignment.springboot.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity addUser(UserEntity user) {
        try{
            return userRepository.save(user);
        }catch (Exception e) {
            log.error("Adding user failed!! due to -> ", e.getMessage());
            return null;
        }
    }
}
