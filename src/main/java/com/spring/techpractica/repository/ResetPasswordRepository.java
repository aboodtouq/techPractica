package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long> {
    Optional<ResetPassword> getResetPasswordByResetPasswordId(Long resetId);
}
