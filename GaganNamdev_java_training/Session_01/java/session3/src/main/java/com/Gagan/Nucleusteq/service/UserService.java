package com.Gagan.Nucleusteq.service;

import com.Gagan.Nucleusteq.model.User;
import com.Gagan.Nucleusteq.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    // constructor injection (MANDATORY) - Spring will automatically inject the UserRepository bean here
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // SEARCH USERS
    // This method allows searching for users based on optional criteria: name, age, and role.
    public List<User> searchUsers(String name, Integer age, String role) {

        List<User> allUsers = repository.getAllUsers();
        List<User> result = new ArrayList<>();

        // Iterate through all users and check if they match the provided criteria
        for (User user : allUsers) {

            boolean match = true;

            if (name != null && !user.getName().equalsIgnoreCase(name)) {
                match = false;
            }

            if (age != null && !user.getAge().equals(age)) {
                match = false;
            }

            if (role != null && !user.getRole().equalsIgnoreCase(role)) {
                match = false;
            }

            if (match) {
                result.add(user);
            }
        }

        return result;
    }

    // SUBMIT USER
    // This method allows submitting a new user. It validates the input data and returns appropriate responses.
    //uses exception handling to manage invalid input scenarios,
    //  ensuring that clients receive clear feedback on what went wrong.

    public String submitUser(User user) {

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new com.Gagan.Nucleusteq.exception.CustomException("Invalid name");
        }

        if (user.getAge() == null) {
            throw new com.Gagan.Nucleusteq.exception.CustomException("Invalid age");
        }

        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            throw new com.Gagan.Nucleusteq.exception.CustomException("Invalid role");
        }

        return "User submitted successfully";
    }

    // DELETE USER
    // This method allows deleting a user by their ID. It requires a confirmation parameter to prevent accidental deletions.
    public String deleteUser(Long id, boolean confirm) {

        if (!confirm) {
            return "Confirmation required. Pass confirm=true to delete user.";
        }

        repository.deleteUser(id);
        return "User deleted successfully";
    }
}