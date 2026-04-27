package com.example.reimbursementportal.service;

import com.example.reimbursementportal.dto.ClaimRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import org.springframework.data.domain.Page;

public interface ClaimService {
    ClaimResponseDto submitClaim(Long employeeId, ClaimRequestDto claimRequestDto);
    Page<ClaimResponseDto> getAssignedClaims(Long reviewerId, int page, int size);
}