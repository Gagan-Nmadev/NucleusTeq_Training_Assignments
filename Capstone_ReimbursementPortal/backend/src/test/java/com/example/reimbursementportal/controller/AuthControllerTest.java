package com.example.reimbursementportal.controller;

import com.example.reimbursementportal.dto.LoginRequestDto;
import com.example.reimbursementportal.dto.LoginResponseDto;
import com.example.reimbursementportal.enums.Role;
import com.example.reimbursementportal.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for AuthController.
 *
 * We test login API using MockMvc without starting full server.
 */
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test login API success response.
     */
    @Test
    void login_shouldReturnOk_whenCredentialsAreValid() throws Exception {
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("admin@company.com");
        request.setPassword("admin123");

        LoginResponseDto response = new LoginResponseDto(
                1L,
                "Admin",
                "admin@company.com",
                Role.ADMIN,
                "Login successful"
        );

        when(authService.login(request)).thenReturn(response);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}