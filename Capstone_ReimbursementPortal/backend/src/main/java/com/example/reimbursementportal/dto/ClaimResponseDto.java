package com.example.reimbursementportal.dto;

import com.example.reimbursementportal.enums.ClaimStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClaimResponseDto {

    private Long id;
    private BigDecimal amount;
    private LocalDate claimDate;
    private String description;
    private ClaimStatus status;
    private Long employeeId;
    private Long reviewerId;
    private String reviewerComment;

    public ClaimResponseDto() {
    }

    public ClaimResponseDto(Long id, BigDecimal amount, LocalDate claimDate, String description,
                            ClaimStatus status, Long employeeId, Long reviewerId, String reviewerComment) {
        this.id = id;
        this.amount = amount;
        this.claimDate = claimDate;
        this.description = description;
        this.status = status;
        this.employeeId = employeeId;
        this.reviewerId = reviewerId;
        this.reviewerComment = reviewerComment;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public String getDescription() {
        return description;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public String getReviewerComment() {
        return reviewerComment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public void setReviewerComment(String reviewerComment) {
        this.reviewerComment = reviewerComment;
    }
}