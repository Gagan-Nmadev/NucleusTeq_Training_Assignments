package com.example.reimbursementportal.service.impl;

import com.example.reimbursementportal.constant.AppConstants;
import com.example.reimbursementportal.dto.ClaimActionRequestDto;
import com.example.reimbursementportal.dto.ClaimRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.entity.Claim;
import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.enums.ClaimStatus;
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

    public ClaimServiceImpl(ClaimRepository claimRepository,
                            UserRepository userRepository) {

        this.claimRepository = claimRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ClaimResponseDto submitClaim(Long employeeId,
                                        ClaimRequestDto claimRequestDto) {

        User employee = userRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.EMPLOYEE_NOT_FOUND
                        ));

        if (employee.getRole() != Role.EMPLOYEE) {

            throw new BusinessException(
                    AppConstants.ONLY_EMPLOYEE_CAN_SUBMIT_CLAIM
            );
        }

        Long reviewerId;

        if (employee.getManagerId() != null) {

            reviewerId = employee.getManagerId();

        } else {

            User admin = userRepository.findFirstByRole(Role.ADMIN)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    AppConstants.ADMIN_REVIEWER_NOT_FOUND
                            ));

            reviewerId = admin.getId();
        }

        Claim claim = ClaimMapper.toEntity(
                claimRequestDto,
                employee.getId(),
                reviewerId
        );

        Claim savedClaim = claimRepository.save(claim);

        return ClaimMapper.toResponse(savedClaim);
    }

    @Override
    public Page<ClaimResponseDto> getAssignedClaims(Long reviewerId,
                                                    int page,
                                                    int size) {

        userRepository.findById(reviewerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.REVIEWER_NOT_FOUND
                        ));

        return claimRepository.findByReviewerId(
                        reviewerId,
                        PageRequest.of(page, size)
                )
                .map(ClaimMapper::toResponse);
    }

    @Override
    public Page<ClaimResponseDto> getClaimsByEmployee(Long employeeId,
                                                      int page,
                                                      int size) {

        User employee = userRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.EMPLOYEE_NOT_FOUND
                        ));

        if (employee.getRole() != Role.EMPLOYEE) {

            throw new BusinessException(
                    AppConstants.ONLY_EMPLOYEE_CAN_VIEW_CLAIMS
            );
        }

        return claimRepository.findByEmployeeId(
                        employeeId,
                        PageRequest.of(page, size)
                )
                .map(ClaimMapper::toResponse);
    }

    @Override
    public ClaimResponseDto approveClaim(Long claimId,
                                         Long reviewerId,
                                         ClaimActionRequestDto requestDto) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.CLAIM_NOT_FOUND
                        ));

        validateReviewer(claim, reviewerId);

        if (claim.getStatus() != ClaimStatus.SUBMITTED) {

            throw new BusinessException(
                    AppConstants.ONLY_SUBMITTED_CLAIMS_CAN_BE_APPROVED
            );
        }

        claim.setStatus(ClaimStatus.APPROVED);
        claim.setReviewerComment(requestDto.getComment());

        Claim savedClaim = claimRepository.save(claim);

        return ClaimMapper.toResponse(savedClaim);
    }

    @Override
    public ClaimResponseDto rejectClaim(Long claimId,
                                        Long reviewerId,
                                        ClaimActionRequestDto requestDto) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.CLAIM_NOT_FOUND
                        ));

        validateReviewer(claim, reviewerId);

        if (claim.getStatus() != ClaimStatus.SUBMITTED) {

            throw new BusinessException(
                    AppConstants.ONLY_SUBMITTED_CLAIMS_CAN_BE_REJECTED
            );
        }

        if (requestDto.getComment() == null
                || requestDto.getComment().trim().isEmpty()) {

            throw new BusinessException(
                    AppConstants.COMMENT_REQUIRED_FOR_REJECTION
            );
        }

        claim.setStatus(ClaimStatus.REJECTED);
        claim.setReviewerComment(requestDto.getComment());

        Claim savedClaim = claimRepository.save(claim);

        return ClaimMapper.toResponse(savedClaim);
    }

    private void validateReviewer(Claim claim, Long reviewerId) {

        if (claim.getReviewerId() == null
                || !claim.getReviewerId().equals(reviewerId)) {

            throw new BusinessException(
                    AppConstants.NOT_ALLOWED_TO_REVIEW_CLAIM
            );
        }
    }
}