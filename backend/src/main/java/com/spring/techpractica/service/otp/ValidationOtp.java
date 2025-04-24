package com.spring.techpractica.service.otp;

import com.spring.techpractica.dto.otp.OtpVerificationInput;
import com.spring.techpractica.dto.otp.UserSubmitOtp;
import com.spring.techpractica.factory.OtpVerificationInputFactory;
import com.spring.techpractica.model.entity.Otp;
import com.spring.techpractica.validator.OtpValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ValidationOtp {

    private final OtpManagementData otpData;

    private final OtpValidator<LocalDateTime> otpExpirationValidator;

    private final OtpValidator<OtpVerificationInput> otpMatchingValidator;

    @Transactional
    public void validationOtp(UserSubmitOtp userSubmitOtp) {

        Otp otp = otpData.
                getOtpByOtpId(userSubmitOtp.getOtpId());

        OtpVerificationInput otpVerificationInput =
                OtpVerificationInputFactory
                        .createOtpVerificationInput(userSubmitOtp.getOtp(), otp.getOtpCode());

        validationOtp(otpVerificationInput,
                otp.getExpirationDate());

        otpData.deleteOtp(otp);
    }

    private void validationOtp(OtpVerificationInput otpVerificationInput,
                               LocalDateTime otpExpiration) {
        otpExpirationValidator.validate(otpExpiration);
        otpMatchingValidator.validate(otpVerificationInput);
    }

}
