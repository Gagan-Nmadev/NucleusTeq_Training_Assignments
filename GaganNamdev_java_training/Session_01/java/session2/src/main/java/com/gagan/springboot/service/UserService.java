package com.gagan.springboot.service;

import org.springframework.stereotype.Service;
import java.util.*;
import com.gagan.springboot.repository.UserRepository;
import com.gagan.springboot.model.User;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getUsers() {
        return repo.getAllUsers();
    }

    public User getUser(int id) {
        return repo.getUserById(id);
    }

    public String createUser(User user) {
        repo.addUser(user);
        return "User added successfully";
    }
}