package com.example.reimbursementportal.service.impl;

import com.example.reimbursementportal.dto.UserRequestDto;
import com.example.reimbursementportal.dto.UserResponseDto;
import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.enums.Role;
import com.example.reimbursementportal.exception.BusinessException;
import com.example.reimbursementportal.exception.ResourceNotFoundException;
import com.example.reimbursementportal.mapper.UserMapper;
import com.example.reimbursementportal.repository.UserRepository;
import com.example.reimbursementportal.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String COMPANY_DOMAIN = "@company.com";

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        validateCompanyEmail(userRequestDto.getEmail());

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new BusinessException("Email already exists");
        }

        if (userRequestDto.getRole() == Role.EMPLOYEE && userRequestDto.getManagerId() != null) {
            User manager = userRepository.findById(userRequestDto.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

            if (manager.getRole() != Role.MANAGER) {
                throw new BusinessException("Assigned user is not a manager");
            }
        }

        User user = UserMapper.toEntity(userRequestDto);
        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(existingUser);
    }

    private void validateCompanyEmail(String email) {
        if (!email.endsWith(COMPANY_DOMAIN)) {
            throw new BusinessException("Email must belong to company domain");
        }
    }
}