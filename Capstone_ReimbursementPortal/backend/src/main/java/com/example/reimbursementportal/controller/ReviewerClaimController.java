package com.example.reimbursementportal.controller;

import com.example.reimbursementportal.dto.ClaimResponseDto;
import com.example.reimbursementportal.service.ClaimService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviewer/claims")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class ReviewerClaimController {

    private final ClaimService claimService;

    public ReviewerClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public ResponseEntity<Page<ClaimResponseDto>> getAssignedClaims(
            @RequestParam Long reviewerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(claimService.getAssignedClaims(reviewerId, page, size));
    }
}