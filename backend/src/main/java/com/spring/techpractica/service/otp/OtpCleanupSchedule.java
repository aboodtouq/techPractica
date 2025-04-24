package com.spring.techpractica.service.otp;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class OtpCleanupSchedule {

    private final OtpService otpService;

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void removeExpireOtps() {
        otpService.deleteOtpByExpirationTimeBefore(LocalDateTime.now());
    }

}
