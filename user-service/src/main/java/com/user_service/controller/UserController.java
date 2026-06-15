package com.user_service.controller;

import com.user_service.kafka.UserProducer;
import com.user_service.model.User;
import com.user_service.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserController(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        User saved = userRepository.save(user);
        userProducer.sendUserCreated(saved.getId().toString());
        return saved;
    }
}
