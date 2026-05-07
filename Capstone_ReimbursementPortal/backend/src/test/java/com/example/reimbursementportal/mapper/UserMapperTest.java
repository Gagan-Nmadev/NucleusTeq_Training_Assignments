package com.example.reimbursementportal.mapper;

import com.example.reimbursementportal.dto.UserRequestDto;
import com.example.reimbursementportal.dto.UserResponseDto;
import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.enums.Role;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for UserMapper.
 *
 * We test conversion between:
 * - UserRequestDto to User entity
 * - User entity to UserResponseDto
 */
class UserMapperTest {

    /**
     * Test mapping from request DTO to User entity.
     */
    @Test
    void toEntity_shouldConvertRequestDtoToUserEntity() {
        UserRequestDto request = new UserRequestDto();
        request.setName("Gagan");
        request.setEmail("gagan@company.com");
        request.setPassword("pass123");
        request.setRole(Role.EMPLOYEE);
        request.setManagerId(2L);

        User user = UserMapper.toEntity(request);

        assertThat(user.getName()).isEqualTo("Gagan");
        assertThat(user.getEmail()).isEqualTo("gagan@company.com");
        assertThat(user.getPassword()).isEqualTo("pass123");
        assertThat(user.getRole()).isEqualTo(Role.EMPLOYEE);
        assertThat(user.getManagerId()).isEqualTo(2L);
    }

    /**
     * Test mapping from User entity to response DTO.
     */
    @Test
    void toResponse_shouldConvertUserEntityToResponseDto() {
        User user = new User();
        user.setId(1L);
        user.setName("Gagan");
        user.setEmail("gagan@company.com");
        user.setRole(Role.ADMIN);
        user.setManagerId(null);

        UserResponseDto response = UserMapper.toResponse(user);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Gagan");
        assertThat(response.getEmail()).isEqualTo("gagan@company.com");
        assertThat(response.getRole()).isEqualTo(Role.ADMIN);
    }
}
