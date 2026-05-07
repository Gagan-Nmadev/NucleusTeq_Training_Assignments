package com.example.reimbursementportal.dto;

import com.example.reimbursementportal.enums.Role;

public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private Long managerId;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String name, String email, Role role, Long managerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.managerId = managerId;
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

    public Long getManagerId() {
        return managerId;
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

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}