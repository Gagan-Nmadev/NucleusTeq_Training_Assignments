package com.example.reimbursementportal.service.impl;

import com.example.reimbursementportal.dto.LoginRequestDto;
import com.example.reimbursementportal.dto.LoginResponseDto;
import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.enums.Role;
import com.example.reimbursementportal.exception.BusinessException;
import com.example.reimbursementportal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AuthServiceImpl.
 *
 * We test authentication related business logic:
 * - Successful login
 * - Invalid email
 * - Wrong password
 *
 * Mockito is used to mock repository behavior.
 */

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;


    /**
     * Test successful login when valid email and password are provided.
     */
    @Test
    void login_shouldReturnUserDetails_whenCredentialsAreValid() {
        // Arrange
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("admin@company.com");
        request.setPassword("admin123");

        User user = new User();
        user.setId(1L);
        user.setName("Admin User");
        user.setEmail("admin@company.com");
        user.setPassword("admin123");
        user.setRole(Role.ADMIN);

        when(userRepository.findByEmail("admin@company.com"))
                .thenReturn(Optional.of(user));

        // Act
        LoginResponseDto response = authService.login(request);

        // Assert
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getEmail()).isEqualTo("admin@company.com");
        assertThat(response.getRole()).isEqualTo(Role.ADMIN);
    }

    /**
     * Test login failure when email does not exist in database.
     */
    @Test
    void login_shouldThrowException_whenEmailIsInvalid() {
        // Arrange
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("wrong@company.com");
        request.setPassword("admin123");

        when(userRepository.findByEmail("wrong@company.com"))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(BusinessException.class);
    }

    /**
     * Test login failure when password is incorrect.
     */

    @Test
    void login_shouldThrowException_whenPasswordIsWrong() {
        // Arrange
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("admin@company.com");
        request.setPassword("wrongpass");

        User user = new User();
        user.setEmail("admin@company.com");
        user.setPassword("admin123");
        user.setRole(Role.ADMIN);

        when(userRepository.findByEmail("admin@company.com"))
                .thenReturn(Optional.of(user));

        // Act + Assert
        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(BusinessException.class);
    }
}