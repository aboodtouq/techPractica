package com.spring.techpractica.validator.otpValidator;

import com.spring.techpractica.dto.otp.OtpVerificationInput;
import org.springframework.stereotype.Component;

@Component
public class OtpMatchValidator extends OtpValidator<OtpVerificationInput> {

//
    @Override
    public void validate(OtpVerificationInput otps) {
        if (otps.getStoredOtp() == null || otps.getUserOtp() == null) {
            throw new IllegalArgumentException("OTP values cannot be null");
        }

        if (!otps.getStoredOtp().equals(otps.getUserOtp())) {
            throw new IllegalArgumentException("The provided OTP does not match the stored OTP.");
        }

    }
}
