package com.spring.techpractica.service;

import com.spring.techpractica.dto.OtpRequest;
import com.spring.techpractica.dto.ResetPasswordRequest;
import com.spring.techpractica.dto.ResetPasswordResponse;
import com.spring.techpractica.model.entity.ResetPassword;
import com.spring.techpractica.repository.ResetPasswordRepository;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {

    private final ResetPasswordRepository resetPasswordRepository;

    public ResetPasswordService(ResetPasswordRepository resetPasswordRepository) {
        this.resetPasswordRepository = resetPasswordRepository;
    }

    /*
        //Create Reset Password entity
        //generate code 6 digit
        //isUsed by default False
        //expiration date 5m from created (localDateTime.know)+
     */
    public ResetPasswordResponse createResetPassword(ResetPasswordRequest resetPasswordRequest) {
        ResetPassword resetPassword = new ResetPassword();

        resetPassword.setUserEmail(resetPasswordRequest.getUserEmail());

        String otpCode = String.format("%06d", (int) (Math.random() * 1000000));

        resetPassword.setOtpCode(otpCode);

        resetPassword.setUsed(Boolean.FALSE);

        resetPassword.setExpirationDate(java.time.LocalDateTime.now().plusMinutes(5));

        resetPasswordRepository.save(resetPassword);

        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();

        resetPasswordResponse.setResetId(resetPassword.getResetPasswordId());

        resetPasswordResponse.setUserEmail(resetPassword.getUserEmail());

        return resetPasswordResponse;
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