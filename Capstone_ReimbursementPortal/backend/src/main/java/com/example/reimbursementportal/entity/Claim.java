package com.example.reimbursementportal.entity;

import com.example.reimbursementportal.enums.ClaimStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private LocalDate claimDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    private Long employeeId;

    private Long reviewerId;

    private String reviewerComment;

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