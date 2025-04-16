package com.spring.techpractica.service;

import com.spring.techpractica.dto.otp.OtpResponse;
import com.spring.techpractica.dto.otp.UserSendOtp;
import com.spring.techpractica.dto.otp.UserSubmitOtp;
import com.spring.techpractica.exception.AuthenticationException;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.model.entity.Otp;
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
    public OtpResponse createResetPassword(UserSendOtp userSendOtp) {

        userService.findUserByUserEmail(userSendOtp.getUserEmail())
                .orElseThrow(() -> new AuthenticationException("User Not Found In System !!"));

        Otp otp = Otp.builder()
                .userEmail(userSendOtp.getUserEmail())
                .build();

        resetPasswordRepository.save(otp);

        return OtpResponse.builder()
                .otpId(otp.getOtpId())
                .userEmail(otp.getUserEmail())
                .build();

    }

    public void resetPasswordValid(Otp otp) {

        if (otp.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new AuthenticationException("Expired Expiration Date");
        }
    }

    @Transactional
    public void validationOtp(UserSubmitOtp userSubmitOtp) {

        Otp otp = resetPasswordRepository.
                getResetPasswordByResetPasswordId(userSubmitOtp.getResetPasswordId())
                .orElseThrow(() -> new ResourcesNotFoundException("Not found OTP"));

        String submittedOTP = userSubmitOtp.getOtp();
        String storedOTP = otp.getOtpCode();

        if (!submittedOTP.equals(storedOTP)) {
            throw new AuthenticationException("Wrong OTP");
        }

        resetPasswordValid(otp);
        resetPasswordRepository.delete(otp);
    }

    //query delete resetPasswordByExpirationTimeBefore
    public void deleteResetPasswordByExpirationTimeBefore(LocalDateTime now) {

    }
}