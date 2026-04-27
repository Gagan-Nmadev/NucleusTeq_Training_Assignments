package com.example.reimbursementportal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClaimRequestDto {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Claim date is required")
    private LocalDate claimDate;

    @NotBlank(message = "Description is required")
    private String description;

    public ClaimRequestDto() {
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}