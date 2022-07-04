package com.example.propertymanager.controller;

import com.example.propertymanager.model.User;
import com.example.propertymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 **/
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User persistedUser = userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
