package com.example.reimbursementportal.controller;

import com.example.reimbursementportal.dto.ClaimRequestDto;
import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.service.ClaimService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee/claims")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class EmployeeClaimController {

    private final ClaimService claimService;

    public EmployeeClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<ClaimResponseDto> submitClaim(
            @PathVariable Long employeeId,
            @Valid @RequestBody ClaimRequestDto claimRequestDto) {

        ClaimResponseDto response = claimService.submitClaim(employeeId, claimRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}