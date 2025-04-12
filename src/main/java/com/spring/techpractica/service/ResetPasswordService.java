package com.spring.techpractica.service;

import com.spring.techpractica.dto.restpassword.OtpRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordResponse;
import com.spring.techpractica.exception.AuthenticationException;
import com.spring.techpractica.model.entity.ResetPassword;
import com.spring.techpractica.repository.ResetPasswordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResetPasswordService {

    private final ResetPasswordRepository resetPasswordRepository;

    private final UserService userService;

    public ResetPasswordService(ResetPasswordRepository resetPasswordRepository, UserService userService) {
        this.resetPasswordRepository = resetPasswordRepository;
        this.userService = userService;
    }


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

    public boolean isValid() {
        return false;
    }

    /*
    fitch reset password entity and check isOtp valid
     */
    public void submitOtp(OtpRequest otpRequest) {
    }
}