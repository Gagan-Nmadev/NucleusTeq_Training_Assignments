package com.gagan.springboot.repository;

import org.springframework.stereotype.Repository;
import java.util.*;
import com.gagan.springboot.model.User;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1, "Gagan"));
        users.add(new User(2, "Ram"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream()
                .filter(u -> u.id == id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(User user) {
        users.add(user);
    }
}
