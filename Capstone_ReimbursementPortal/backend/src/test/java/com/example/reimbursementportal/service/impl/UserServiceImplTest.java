package com.example.reimbursementportal.service.impl;

import com.example.reimbursementportal.dto.UserRequestDto;
import com.example.reimbursementportal.dto.UserResponseDto;
import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.enums.Role;
import com.example.reimbursementportal.exception.BusinessException;
import com.example.reimbursementportal.exception.ResourceNotFoundException;
import com.example.reimbursementportal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for UserServiceImpl.
 *
 * We test user management business logic:
 * - Create user
 * - Validate company email
 * - Duplicate email handling
 * - Assign manager
 * - Delete user
 */

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    /**
     * Test successful user creation with valid data.
     */

    @Test
    void createUser_shouldCreateUser_whenValidDataGiven() {
        // Arrange
        UserRequestDto request = new UserRequestDto();
        request.setName("Employee One");
        request.setEmail("employee@company.com");
        request.setPassword("emp123");
        request.setRole(Role.EMPLOYEE);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Employee One");
        savedUser.setEmail("employee@company.com");
        savedUser.setPassword("emp123");
        savedUser.setRole(Role.EMPLOYEE);

        when(userRepository.existsByEmail("employee@company.com"))
                .thenReturn(false);
        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        // Act
        UserResponseDto response = userService.createUser(request);

        // Assert
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getEmail()).isEqualTo("employee@company.com");
        assertThat(response.getRole()).isEqualTo(Role.EMPLOYEE);
    }


    /**
     * Test exception when email domain is invalid.
     */

    @Test
    void createUser_shouldThrowException_whenEmailDomainIsInvalid() {
        // Arrange
        UserRequestDto request = new UserRequestDto();
        request.setName("Wrong User");
        request.setEmail("wrong@gmail.com");
        request.setPassword("test123");
        request.setRole(Role.EMPLOYEE);

        // Act + Assert
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(BusinessException.class);
    }

    /**
     * Test exception when email already exists.
     */
    @Test
    void createUser_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        UserRequestDto request = new UserRequestDto();
        request.setName("Duplicate User");
        request.setEmail("employee@company.com");
        request.setPassword("test123");
        request.setRole(Role.EMPLOYEE);

        when(userRepository.existsByEmail("employee@company.com"))
                .thenReturn(true);

        // Act + Assert
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(BusinessException.class);
    }

    /**
     * Test fetching all users from database.
     */
    @Test
    void getAllUsers_shouldReturnUserList() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Admin User");
        user.setEmail("admin@company.com");
        user.setRole(Role.ADMIN);

        when(userRepository.findAll()).thenReturn(List.of(user));

        // Act
        List<UserResponseDto> users = userService.getAllUsers();

        // Assert
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getName()).isEqualTo("Admin User");
    }


    /**
     * Test assigning a manager to an employee.
     */
    @Test
    void assignManager_shouldAssignManagerToEmployee() {
        // Arrange
        User employee = new User();
        employee.setId(1L);
        employee.setName("Employee");
        employee.setEmail("employee@company.com");
        employee.setRole(Role.EMPLOYEE);

        User manager = new User();
        manager.setId(2L);
        manager.setName("Manager");
        manager.setEmail("manager@company.com");
        manager.setRole(Role.MANAGER);

        User updatedEmployee = new User();
        updatedEmployee.setId(1L);
        updatedEmployee.setName("Employee");
        updatedEmployee.setEmail("employee@company.com");
        updatedEmployee.setRole(Role.EMPLOYEE);
        updatedEmployee.setManagerId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(userRepository.findById(2L)).thenReturn(Optional.of(manager));
        when(userRepository.save(any(User.class))).thenReturn(updatedEmployee);

        // Act
        UserResponseDto response = userService.assignManager(1L, 2L);

        // Assert
        assertThat(response.getManagerId()).isEqualTo(2L);
    }

    @Test
    void deleteUser_shouldDeleteUser_whenUserExists() {
        // Arrange
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(1L);

        // Assert
        verify(userRepository, times(1)).delete(user);
    }


    /**
     * Test successful user deletion.
     */

    @Test
    void deleteUser_shouldThrowException_whenUserNotFound() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> userService.deleteUser(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
