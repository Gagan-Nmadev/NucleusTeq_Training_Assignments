package com.gagan.springboot.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.gagan.springboot.service.UserService;
import com.gagan.springboot.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return service.getUser(id);
    }

    @PostMapping
    public String addUser(@RequestBody User user) {
        return service.createUser(user);
    }
}