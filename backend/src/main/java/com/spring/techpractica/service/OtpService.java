package com.spring.techpractica.service;

import com.spring.techpractica.dto.otp.OtpResponse;
import com.spring.techpractica.dto.otp.UserSendOtp;
import com.spring.techpractica.dto.otp.UserSubmitOtp;
import com.spring.techpractica.exception.AuthenticationException;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.model.entity.Otp;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.repository.OtpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;

    private final UserService userService;


    @Transactional
    public OtpResponse createOtp(UserSendOtp userSendOtp) {

        User user = userService.findUserByUserEmail(userSendOtp.getUserEmail())
                .orElseThrow(() -> new AuthenticationException("User Not Found In System !!"));

        Otp otp = Otp.builder()
                .user(user)
                .build();

        otpRepository.save(otp);

        return OtpResponse.builder()
                .otpId(otp.getOtpId())
                .userEmail(otp.getUser().getUserEmail())
                .build();

    }

    public void resetPasswordValid(Otp otp) {

        if (otp.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new AuthenticationException("Expired Expiration Date");
        }
    }

    @Transactional
    public void validationOtp(UserSubmitOtp userSubmitOtp) {

        Otp otp = otpRepository.
                getOtpByOtpId(userSubmitOtp.getOtpId())
                .orElseThrow(() -> new ResourcesNotFoundException("Not found OTP"));

        String submittedOTP = userSubmitOtp.getOtp();
        String storedOTP = otp.getOtpCode();

        if (!submittedOTP.equals(storedOTP)) {
            throw new AuthenticationException("Wrong OTP");
        }

        resetPasswordValid(otp);
        otpRepository.delete(otp);
    }

    //query delete resetPasswordByExpirationTimeBefore

    public void deleteOtpByExpirationTimeBefore(LocalDateTime now) {
        otpRepository.deleteOtpsByExpirationDateBefore(now);
    }
}