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

           return null;
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
