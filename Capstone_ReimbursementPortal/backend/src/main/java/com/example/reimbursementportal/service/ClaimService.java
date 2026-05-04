package com.example.reimbursementportal.service;

import com.example.reimbursementportal.dto.ClaimRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.dto.ClaimActionRequestDto;
import org.springframework.data.domain.Page;

public interface ClaimService {

    ClaimResponseDto submitClaim(Long employeeId, ClaimRequestDto claimRequestDto);

    Page<ClaimResponseDto> getAssignedClaims(Long reviewerId, int page, int size);

    // ✅ NEW METHODS
    ClaimResponseDto approveClaim(Long claimId, Long reviewerId, ClaimActionRequestDto requestDto);

    ClaimResponseDto rejectClaim(Long claimId, Long reviewerId, ClaimActionRequestDto requestDto);
}