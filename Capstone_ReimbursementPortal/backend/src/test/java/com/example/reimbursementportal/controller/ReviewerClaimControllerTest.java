package com.example.reimbursementportal.controller;

import com.example.reimbursementportal.dto.ClaimActionRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.enums.ClaimStatus;
import com.example.reimbursementportal.service.ClaimService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for ReviewerClaimController.
 *
 * We test reviewer APIs:
 * - Get assigned claims
 * - Approve claim
 * - Reject claim
 */
@WebMvcTest(ReviewerClaimController.class)
class ReviewerClaimControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClaimService claimService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test assigned claims API.
     */
    @Test
    void getAssignedClaims_shouldReturnOk() throws Exception {
        when(claimService.getAssignedClaims(eq(2L), eq(0), eq(5)))
                .thenReturn(new PageImpl<>(List.of()));

        mockMvc.perform(get("/api/reviewer/claims?reviewerId=2&page=0&size=5"))
                .andExpect(status().isOk());
    }

    /**
     * Test approve claim API.
     */
    @Test
    void approveClaim_shouldReturnOk() throws Exception {
        ClaimActionRequestDto request = new ClaimActionRequestDto();
        request.setComment("Approved");

        ClaimResponseDto response = buildClaimResponse(ClaimStatus.APPROVED);

        when(claimService.approveClaim(eq(1L), eq(2L), any(ClaimActionRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/reviewer/claims/1/approve?reviewerId=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    /**
     * Test reject claim API.
     */
    @Test
    void rejectClaim_shouldReturnOk() throws Exception {
        ClaimActionRequestDto request = new ClaimActionRequestDto();
        request.setComment("Invalid bill");

        ClaimResponseDto response = buildClaimResponse(ClaimStatus.REJECTED);

        when(claimService.rejectClaim(eq(1L), eq(2L), any(ClaimActionRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/reviewer/claims/1/reject?reviewerId=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    private ClaimResponseDto buildClaimResponse(ClaimStatus status) {
        ClaimResponseDto response = new ClaimResponseDto();
        response.setId(1L);
        response.setAmount(BigDecimal.valueOf(1000));
        response.setClaimDate(LocalDate.now());
        response.setDescription("Travel claim");
        response.setStatus(status);
        response.setEmployeeId(5L);
        response.setReviewerId(2L);
        response.setReviewerComment("Done");
        return response;
    }
}
