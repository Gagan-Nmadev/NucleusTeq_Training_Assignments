package com.example.reimbursementportal.service.impl;

import com.example.reimbursementportal.constant.AppConstants;
import com.example.reimbursementportal.dto.LoginRequestDto;
import com.example.reimbursementportal.dto.LoginResponseDto;
import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.exception.BusinessException;
import com.example.reimbursementportal.repository.UserRepository;
import com.example.reimbursementportal.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() ->
                        new BusinessException(AppConstants.INVALID_EMAIL_OR_PASSWORD));

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new BusinessException(AppConstants.INVALID_EMAIL_OR_PASSWORD);
        }

        return new LoginResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                AppConstants.LOGIN_SUCCESSFUL
        );
    }
}