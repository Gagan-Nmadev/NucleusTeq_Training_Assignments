package com.Gagan.Nucleusteq.controller;

import com.Gagan.Nucleusteq.model.User;
import com.Gagan.Nucleusteq.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    // constructor injection
    public UserController(UserService service) {
        this.service = service;
    }

    // SEARCH API for users

    // This API allows clients to search for users based on optional criteria: name, age, and role.
    @GetMapping("/search")
    public List<User> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String role) {

        return service.searchUsers(name, age, role);
    }


    // Submit API for creating user
    // updates the submitUser method to include validation checks for the user's name, age, and role.
    // If any of these fields are invalid (e.g., name is null or empty, age is null, or role is null or empty),

    @PostMapping("/submit")
    public org.springframework.http.ResponseEntity<String> submitUser(@RequestBody User user) {

        String response = service.submitUser(user);
        return org.springframework.http.ResponseEntity.status(201).body(response);
    }

    // DELETE API
    // This API allows clients to delete a user by their ID. A confirmation parameter is required.
    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "false") boolean confirm) {

        return service.deleteUser(id, confirm);
    }
}