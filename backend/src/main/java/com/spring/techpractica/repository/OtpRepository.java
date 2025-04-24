package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> getOtpByOtpId(Long resetId);

    void deleteOtpsByExpirationDateBefore(LocalDateTime now);

}
