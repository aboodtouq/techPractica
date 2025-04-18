package com.spring.techpractica.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OtpCleanupSchedule {

    private OtpService otpService;

    //expire otp
    @Scheduled(fixedRate = 5 * 60 * 1000) //every 15m
    public void removeExpireOtps() {
       otpService.deleteOtpByExpirationTimeBefore(LocalDateTime.now());
    }

}
