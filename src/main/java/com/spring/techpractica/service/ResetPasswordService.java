package com.spring.techpractica.service;

import com.spring.techpractica.dto.restpassword.OtpRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordResponse;
import com.spring.techpractica.exception.AuthenticationException;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.model.entity.ResetPassword;
import com.spring.techpractica.repository.ResetPasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ResetPasswordService {

    private final ResetPasswordRepository resetPasswordRepository;

    private final UserService userService;


    @Transactional
    public ResetPasswordResponse createResetPassword(ResetPasswordRequest resetPasswordRequest) {

        userService.findUserByUserEmail(resetPasswordRequest.getUserEmail())
                .orElseThrow(() -> new AuthenticationException("User Not Found In System !!"));

        ResetPassword resetPassword = ResetPassword.builder()
                .userEmail(resetPasswordRequest.getUserEmail())
                .build();

        resetPasswordRepository.save(resetPassword);

        return ResetPasswordResponse.builder()
                .resetId(resetPassword.getResetPasswordId())
                .userEmail(resetPassword.getUserEmail())
                .build();

    }

    public void resetPasswordValid(ResetPassword resetPassword) {
        if (resetPassword.isUsed()) {
            throw new AuthenticationException("Reset Password Used");
        }

        if (resetPassword.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new AuthenticationException("Expired Expiration Date");
        }
    }

    public void validationOtp(OtpRequest otpRequest) {

        ResetPassword resetPassword = resetPasswordRepository.
                getResetPasswordByResetPasswordId(otpRequest.getResetPasswordId())
                .orElseThrow(() -> new ResourcesNotFoundException("Not found OTP"));

        String submittedOTP = otpRequest.getOtp();
        String storedOTP = resetPassword.getOtpCode();

        if (!submittedOTP.equals(storedOTP)) {
            throw new AuthenticationException("Wrong OTP");
        }

        resetPasswordValid(resetPassword);
    }
}