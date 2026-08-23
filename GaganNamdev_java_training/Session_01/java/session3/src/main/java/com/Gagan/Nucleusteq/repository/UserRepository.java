package com.Gagan.Nucleusteq.repository;

import com.Gagan.Nucleusteq.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepository {

    private List<User> users;

    // constructor
    public UserRepository() {

        users = new ArrayList<>();

        users.add(new User(1L, "Gagan", 25, "USER"));
        users.add(new User(2L, "Rahul", 30, "ADMIN"));
        users.add(new User(3L, "Amit", 28, "USER"));
        users.add(new User(4L, "Sneha", 30, "USER"));
        users.add(new User(5L, "Karan", 35, "ADMIN"));
    }

    // get all users
    public List<User> getAllUsers() {

        return users;
    }

    // delete user
    public void deleteUser(Long id) {

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId().equals(id)) {
                users.remove(i);

                break;
            }
        }
    }
}
