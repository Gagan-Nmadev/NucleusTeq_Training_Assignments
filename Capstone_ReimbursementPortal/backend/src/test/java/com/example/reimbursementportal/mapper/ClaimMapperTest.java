package com.example.reimbursementportal.mapper;

import com.example.reimbursementportal.dto.ClaimRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.entity.Claim;
import com.example.reimbursementportal.enums.ClaimStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for ClaimMapper.
 *
 * We test conversion between:
 * - ClaimRequestDto to Claim entity
 * - Claim entity to ClaimResponseDto
 */
class ClaimMapperTest {

    /**
     * Test mapping from claim request DTO to Claim entity.
     */
    @Test
    void toEntity_shouldConvertRequestDtoToClaimEntity() {
        ClaimRequestDto request = new ClaimRequestDto();
        request.setAmount(BigDecimal.valueOf(1500));
        request.setClaimDate(LocalDate.of(2026, 5, 7));
        request.setDescription("Travel claim");

        Claim claim = ClaimMapper.toEntity(request, 5L, 2L);

        assertThat(claim.getAmount()).isEqualTo(BigDecimal.valueOf(1500));
        assertThat(claim.getClaimDate()).isEqualTo(LocalDate.of(2026, 5, 7));
        assertThat(claim.getDescription()).isEqualTo("Travel claim");
        assertThat(claim.getEmployeeId()).isEqualTo(5L);
        assertThat(claim.getReviewerId()).isEqualTo(2L);
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.SUBMITTED);
    }

    /**
     * Test mapping from Claim entity to response DTO.
     */
    @Test
    void toResponse_shouldConvertClaimEntityToResponseDto() {
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setAmount(BigDecimal.valueOf(2000));
        claim.setClaimDate(LocalDate.of(2026, 5, 7));
        claim.setDescription("Food claim");
        claim.setStatus(ClaimStatus.APPROVED);
        claim.setEmployeeId(5L);
        claim.setReviewerId(2L);
        claim.setReviewerComment("Approved");

        ClaimResponseDto response = ClaimMapper.toResponse(claim);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getAmount()).isEqualTo(BigDecimal.valueOf(2000));
        assertThat(response.getDescription()).isEqualTo("Food claim");
        assertThat(response.getStatus()).isEqualTo(ClaimStatus.APPROVED);
        assertThat(response.getEmployeeId()).isEqualTo(5L);
        assertThat(response.getReviewerId()).isEqualTo(2L);
        assertThat(response.getReviewerComment()).isEqualTo("Approved");
    }
}