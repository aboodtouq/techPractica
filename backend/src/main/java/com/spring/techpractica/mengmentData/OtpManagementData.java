package com.spring.techpractica.mengmentData;

import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.model.entity.Otp;
import com.spring.techpractica.repository.OtpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class OtpManagementData {

    private OtpRepository otpRepository;

    public Otp saveOtp(Otp otp) {
        return otpRepository.save(otp);
    }

    public Otp getOtpByOtpId(Long otpId) {
        return otpRepository.findById(otpId)
                .orElseThrow(() -> new ResourcesNotFoundException("Otp not found"));
    }

    public void deleteOtp(Otp otp) {
        otpRepository.delete(otp);
    }

    public void deleteOtpByExpirationDate(LocalDateTime expirationDate) {
        otpRepository.deleteOtpsByExpirationDateBefore(expirationDate);
    }
}
