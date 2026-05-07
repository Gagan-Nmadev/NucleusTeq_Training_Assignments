package com.example.reimbursementportal.service.impl;

import com.example.reimbursementportal.constant.AppConstants;
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

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        validateCompanyEmail(userRequestDto.getEmail());

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new BusinessException(AppConstants.EMAIL_ALREADY_EXISTS);
        }

        if (userRequestDto.getRole() == Role.EMPLOYEE
                && userRequestDto.getManagerId() != null) {

            User manager = userRepository.findById(userRequestDto.getManagerId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(AppConstants.MANAGER_NOT_FOUND));

            if (manager.getRole() != Role.MANAGER) {
                throw new BusinessException(AppConstants.ASSIGNED_USER_IS_NOT_MANAGER);
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
    public UserResponseDto assignManager(Long employeeId, Long managerId) {

        User employee = userRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.EMPLOYEE_NOT_FOUND));

        if (employee.getRole() != Role.EMPLOYEE) {
            throw new BusinessException(
                    AppConstants.MANAGER_CAN_BE_ASSIGNED_ONLY_TO_EMPLOYEE
            );
        }

        User manager = userRepository.findById(managerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.MANAGER_NOT_FOUND));

        if (manager.getRole() != Role.MANAGER) {
            throw new BusinessException(
                    AppConstants.SELECTED_USER_IS_NOT_MANAGER
            );
        }

        employee.setManagerId(manager.getId());

        User updatedEmployee = userRepository.save(employee);

        return UserMapper.toResponse(updatedEmployee);
    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));

        userRepository.delete(existingUser);
    }

    private void validateCompanyEmail(String email) {

        if (!email.endsWith(AppConstants.COMPANY_EMAIL_DOMAIN)) {

            throw new BusinessException(
                    AppConstants.EMAIL_MUST_BELONG_TO_COMPANY_DOMAIN
            );
        }
    }
}