package com.example.reimbursementportal.controller;

import com.example.reimbursementportal.dto.ApiResponseDto;
import com.example.reimbursementportal.dto.UserRequestDto;
import com.example.reimbursementportal.dto.UserResponseDto;
import com.example.reimbursementportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<UserResponseDto>> createUser(
            @Valid @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto response = userService.createUser(userRequestDto);

        return new ResponseEntity<>(
                new ApiResponseDto<>("User created successfully", response),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<Object>> getAllUsers() {
        return ResponseEntity.ok(
                new ApiResponseDto<>("Users fetched successfully", userService.getAllUsers())
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<Object>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(
                new ApiResponseDto<>("User deleted successfully", null)
        );
    }
}