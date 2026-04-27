package com.example.reimbursementportal.repository;

import com.example.reimbursementportal.entity.Claim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Page<Claim> findByReviewerId(Long reviewerId, Pageable pageable);
}