package com.example.reimbursementportal.service.impl;

import com.example.reimbursementportal.dto.ClaimActionRequestDto;
import com.example.reimbursementportal.dto.ClaimRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.entity.Claim;
import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.enums.ClaimStatus;
import com.example.reimbursementportal.enums.Role;
import com.example.reimbursementportal.exception.BusinessException;
import com.example.reimbursementportal.repository.ClaimRepository;
import com.example.reimbursementportal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ClaimServiceImpl.
 *
 * We test claim management business logic:
 * - Submit claim
 * - Get employee claims
 * - Get reviewer claims
 * - Approve claims
 * - Reject claims
 * - Validation and error handling
 */

@ExtendWith(MockitoExtension.class)
class ClaimServiceImplTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClaimServiceImpl claimService;
    /**
     * Test successful claim submission by employee.
     */
    @Test
    void submitClaim_shouldSubmitClaim_whenEmployeeHasManager() {
        // Arrange
        User employee = new User();
        employee.setId(5L);
        employee.setRole(Role.EMPLOYEE);
        employee.setManagerId(2L);

        ClaimRequestDto request = new ClaimRequestDto();
        request.setAmount(BigDecimal.valueOf(2000));
        request.setClaimDate(LocalDate.now());
        request.setDescription("Travel claim");

        Claim savedClaim = new Claim();
        savedClaim.setId(1L);
        savedClaim.setAmount(request.getAmount());
        savedClaim.setClaimDate(request.getClaimDate());
        savedClaim.setDescription(request.getDescription());
        savedClaim.setStatus(ClaimStatus.SUBMITTED);
        savedClaim.setEmployeeId(5L);
        savedClaim.setReviewerId(2L);

        when(userRepository.findById(5L)).thenReturn(Optional.of(employee));
        when(claimRepository.save(any(Claim.class))).thenReturn(savedClaim);

        // Act
        ClaimResponseDto response = claimService.submitClaim(5L, request);

        // Assert
        assertThat(response.getStatus()).isEqualTo(ClaimStatus.SUBMITTED);
        assertThat(response.getReviewerId()).isEqualTo(2L);
    }
    /**
     * Test exception when non-employee tries to submit a claim.
     */
    @Test
    void submitClaim_shouldThrowException_whenUserIsNotEmployee() {
        // Arrange
        User manager = new User();
        manager.setId(2L);
        manager.setRole(Role.MANAGER);

        ClaimRequestDto request = new ClaimRequestDto();
        request.setAmount(BigDecimal.valueOf(1000));
        request.setClaimDate(LocalDate.now());
        request.setDescription("Invalid claim");

        when(userRepository.findById(2L)).thenReturn(Optional.of(manager));

        // Act + Assert
        assertThatThrownBy(() -> claimService.submitClaim(2L, request))
                .isInstanceOf(BusinessException.class);
    }
    /**
     * Test fetching claims assigned to reviewer.
     */
    @Test
    void getAssignedClaims_shouldReturnClaimsForReviewer() {
        // Arrange
        User manager = new User();
        manager.setId(2L);
        manager.setRole(Role.MANAGER);

        Claim claim = new Claim();
        claim.setId(1L);
        claim.setAmount(BigDecimal.valueOf(500));
        claim.setClaimDate(LocalDate.now());
        claim.setDescription("Food claim");
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setEmployeeId(5L);
        claim.setReviewerId(2L);

        Page<Claim> claimPage = new PageImpl<>(List.of(claim));

        when(userRepository.findById(2L)).thenReturn(Optional.of(manager));
        when(claimRepository.findByReviewerId(eq(2L), any(Pageable.class)))
                .thenReturn(claimPage);

        // Act
        Page<ClaimResponseDto> response = claimService.getAssignedClaims(2L, 0, 5);

        // Assert
        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getContent().get(0).getReviewerId()).isEqualTo(2L);
    }
    /**
     * Test fetching claims submitted by employee.
     */
    @Test
    void getClaimsByEmployee_shouldReturnEmployeeClaims() {
        // Arrange
        User employee = new User();
        employee.setId(5L);
        employee.setRole(Role.EMPLOYEE);

        Claim claim = new Claim();
        claim.setId(1L);
        claim.setAmount(BigDecimal.valueOf(800));
        claim.setClaimDate(LocalDate.now());
        claim.setDescription("Cab claim");
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setEmployeeId(5L);
        claim.setReviewerId(2L);

        Page<Claim> claimPage = new PageImpl<>(List.of(claim));

        when(userRepository.findById(5L)).thenReturn(Optional.of(employee));
        when(claimRepository.findByEmployeeId(eq(5L), any(Pageable.class)))
                .thenReturn(claimPage);

        // Act
        Page<ClaimResponseDto> response = claimService.getClaimsByEmployee(5L, 0, 5);

        // Assert
        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getContent().get(0).getEmployeeId()).isEqualTo(5L);
    }
    /**
     * Test approving a submitted claim.
     */
    @Test
    void approveClaim_shouldApproveSubmittedClaim() {
        // Arrange
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setAmount(BigDecimal.valueOf(5000));
        claim.setClaimDate(LocalDate.now());
        claim.setDescription("Hotel claim");
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setEmployeeId(5L);
        claim.setReviewerId(2L);

        ClaimActionRequestDto request = new ClaimActionRequestDto();
        request.setComment("Approved");

        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));
        when(claimRepository.save(any(Claim.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ClaimResponseDto response = claimService.approveClaim(1L, 2L, request);

        // Assert
        assertThat(response.getStatus()).isEqualTo(ClaimStatus.APPROVED);
        assertThat(response.getReviewerComment()).isEqualTo("Approved");
    }
    /**
     * Test rejecting a submitted claim with comment.
     */
    @Test
    void rejectClaim_shouldRejectSubmittedClaim() {
        // Arrange
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setAmount(BigDecimal.valueOf(5000));
        claim.setClaimDate(LocalDate.now());
        claim.setDescription("Hotel claim");
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setEmployeeId(5L);
        claim.setReviewerId(2L);

        ClaimActionRequestDto request = new ClaimActionRequestDto();
        request.setComment("Invalid bill");

        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));
        when(claimRepository.save(any(Claim.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ClaimResponseDto response = claimService.rejectClaim(1L, 2L, request);

        // Assert
        assertThat(response.getStatus()).isEqualTo(ClaimStatus.REJECTED);
        assertThat(response.getReviewerComment()).isEqualTo("Invalid bill");
    }
    /**
     * Test exception when unauthorized reviewer tries to approve claim.
     */
    @Test
    void approveClaim_shouldThrowException_whenReviewerIsWrong() {
        // Arrange
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setReviewerId(2L);

        ClaimActionRequestDto request = new ClaimActionRequestDto();
        request.setComment("Approved");

        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));

        // Act + Assert
        assertThatThrownBy(() -> claimService.approveClaim(1L, 3L, request))
                .isInstanceOf(BusinessException.class);
    }
    /**
     * Test exception when rejection comment is empty.
     */
    @Test
    void rejectClaim_shouldThrowException_whenCommentIsEmpty() {
        // Arrange
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setReviewerId(2L);

        ClaimActionRequestDto request = new ClaimActionRequestDto();
        request.setComment("");

        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));

        // Act + Assert
        assertThatThrownBy(() -> claimService.rejectClaim(1L, 2L, request))
                .isInstanceOf(BusinessException.class);
    }
}