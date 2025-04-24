package com.spring.techpractica.service.otp;

import com.spring.techpractica.mengmentData.OtpManagementData;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

@AllArgsConstructor
public class OtpCleanupSchedule {


    private final OtpManagementData otpManagementData;

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void removeExpireOtps() {
        otpManagementData.deleteOtpByExpirationDate(LocalDateTime.now());
    }

}
