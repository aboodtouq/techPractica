package com.spring.techpractica.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

public class OtpCleanupSchedule {

    private ResetPasswordService resetPasswordService;

    //expire otp
    @Scheduled(fixedRate = 5 * 60 * 1000) //every 15m
    public void removeExpireOtps() {
        resetPasswordService.deleteResetPasswordByExpirationTimeBefore(LocalDateTime.now());
    }

}
