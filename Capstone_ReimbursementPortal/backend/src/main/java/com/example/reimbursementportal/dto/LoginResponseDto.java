package com.example.reimbursementportal.dto;

import com.example.reimbursementportal.enums.Role;

public class LoginResponseDto {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private String message;

    public LoginResponseDto() {
    }

    public LoginResponseDto(Long id, String name, String email, Role role, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}