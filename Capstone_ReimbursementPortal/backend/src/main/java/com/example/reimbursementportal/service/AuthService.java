package com.example.reimbursementportal.service;

import com.example.reimbursementportal.dto.LoginRequestDto;
import com.example.reimbursementportal.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}