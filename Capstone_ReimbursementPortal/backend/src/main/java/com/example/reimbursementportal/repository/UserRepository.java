package com.example.reimbursementportal.repository;

import com.example.reimbursementportal.entity.User;
import com.example.reimbursementportal.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByRole(Role role);
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}