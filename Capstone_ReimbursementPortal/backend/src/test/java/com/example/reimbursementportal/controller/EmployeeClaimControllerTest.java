package com.example.reimbursementportal.controller;

import com.example.reimbursementportal.dto.ClaimRequestDto;
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
 * Unit tests for EmployeeClaimController.
 *
 * We test employee claim APIs:
 * - Submit claim
 * - View own claims
 */
@WebMvcTest(EmployeeClaimController.class)
class EmployeeClaimControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClaimService claimService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test submit claim API.
     */
    @Test
    void submitClaim_shouldReturnCreated() throws Exception {
        ClaimRequestDto request = new ClaimRequestDto();
        request.setAmount(BigDecimal.valueOf(1000));
        request.setClaimDate(LocalDate.now());
        request.setDescription("Travel claim");

        ClaimResponseDto response = new ClaimResponseDto();
        response.setId(1L);
        response.setAmount(BigDecimal.valueOf(1000));
        response.setClaimDate(LocalDate.now());
        response.setDescription("Travel claim");
        response.setStatus(ClaimStatus.SUBMITTED);
        response.setEmployeeId(5L);
        response.setReviewerId(2L);

        when(claimService.submitClaim(eq(5L), any(ClaimRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/employee/claims/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    /**
     * Test employee own claims API.
     */
    @Test
    void getClaimsByEmployee_shouldReturnOk() throws Exception {
        when(claimService.getClaimsByEmployee(eq(5L), eq(0), eq(5)))
                .thenReturn(new PageImpl<>(List.of()));

        mockMvc.perform(get("/api/employee/claims/5?page=0&size=5"))
                .andExpect(status().isOk());
    }
}
