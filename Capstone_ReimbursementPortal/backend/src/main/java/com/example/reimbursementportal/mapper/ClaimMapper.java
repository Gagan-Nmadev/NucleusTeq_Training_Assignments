package com.example.reimbursementportal.mapper;

import com.example.reimbursementportal.dto.ClaimRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.entity.Claim;
import com.example.reimbursementportal.enums.ClaimStatus;

public class ClaimMapper {

    private ClaimMapper() {
    }

    public static Claim toEntity(ClaimRequestDto claimRequestDto, Long employeeId, Long reviewerId) {
        Claim claim = new Claim();
        claim.setAmount(claimRequestDto.getAmount());
        claim.setClaimDate(claimRequestDto.getClaimDate());
        claim.setDescription(claimRequestDto.getDescription());
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setEmployeeId(employeeId);
        claim.setReviewerId(reviewerId);
        return claim;
    }

    public static ClaimResponseDto toResponse(Claim claim) {
        return new ClaimResponseDto(
                claim.getId(),
                claim.getAmount(),
                claim.getClaimDate(),
                claim.getDescription(),
                claim.getStatus(),
                claim.getEmployeeId(),
                claim.getReviewerId(),
                claim.getReviewerComment()
        );
    }
}