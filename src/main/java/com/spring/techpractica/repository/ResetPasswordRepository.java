package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long> {
    ResetPassword getResetPasswordByResetPasswordId(Long resetId);
}
