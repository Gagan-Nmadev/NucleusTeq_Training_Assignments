package com.example.reimbursementportal.service.impl;

import com.example.reimbursementportal.dto.ClaimRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.entity.Claim;
import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.enums.Role;
import com.example.reimbursementportal.exception.BusinessException;
import com.example.reimbursementportal.exception.ResourceNotFoundException;
import com.example.reimbursementportal.mapper.ClaimMapper;
import com.example.reimbursementportal.repository.ClaimRepository;
import com.example.reimbursementportal.repository.UserRepository;
import com.example.reimbursementportal.service.ClaimService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final UserRepository userRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository, UserRepository userRepository) {
        this.claimRepository = claimRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ClaimResponseDto submitClaim(Long employeeId, ClaimRequestDto claimRequestDto) {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (employee.getRole() != Role.EMPLOYEE) {
            throw new BusinessException("Only employee can submit claim");
        }

        Long reviewerId;

        if (employee.getManagerId() != null) {
            reviewerId = employee.getManagerId();
        } else {
            User admin = userRepository.findFirstByRole(Role.ADMIN)
                    .orElseThrow(() -> new ResourceNotFoundException("Admin reviewer not found"));

            reviewerId = admin.getId();
        }

        Claim claim = ClaimMapper.toEntity(claimRequestDto, employee.getId(), reviewerId);
        Claim savedClaim = claimRepository.save(claim);

        return ClaimMapper.toResponse(savedClaim);
    }

    @Override
    public Page<ClaimResponseDto> getAssignedClaims(Long reviewerId, int page, int size) {
        userRepository.findById(reviewerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reviewer not found"));

        return claimRepository.findByReviewerId(reviewerId, PageRequest.of(page, size))
                .map(ClaimMapper::toResponse);
    }
}