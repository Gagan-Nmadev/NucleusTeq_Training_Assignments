package com.example.reimbursementportal.controller;

import com.example.reimbursementportal.dto.UserRequestDto;
import com.example.reimbursementportal.dto.UserResponseDto;
import com.example.reimbursementportal.enums.Role;
import com.example.reimbursementportal.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for AdminController.
 *
 * We test admin user APIs:
 * - Create user
 * - Get all users
 * - Delete user
 */
@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test create user API.
     */
    @Test
    void createUser_shouldReturnCreated() throws Exception {
        UserRequestDto request = new UserRequestDto();
        request.setName("Employee");
        request.setEmail("employee@company.com");
        request.setPassword("pass123");
        request.setRole(Role.EMPLOYEE);

        UserResponseDto response = new UserResponseDto();
        response.setId(1L);
        response.setName("Employee");
        response.setEmail("employee@company.com");
        response.setRole(Role.EMPLOYEE);

        when(userService.createUser(any(UserRequestDto.class))).thenReturn(response);

        mockMvc.perform(post("/api/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    /**
     * Test get all users API.
     */
    @Test
    void getAllUsers_shouldReturnOk() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isOk());
    }

    /**
     * Test delete user API.
     */
    @Test
    void deleteUser_shouldReturnOk() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/admin/users/1"))
                .andExpect(status().isOk());
    }
}