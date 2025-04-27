package com.spring.techpractica.factory;

import com.spring.techpractica.dto.otp.OtpVerificationInput;


public class OtpVerificationInputFactory {

    private OtpVerificationInputFactory() {
    }

    public static OtpVerificationInput createOtpVerificationInput(String storedDatabase,
                                                                  String otpUser) {
        return OtpVerificationInput
                .builder()
                .storedOtp(storedDatabase)
                .userOtp(otpUser)
                .build();
    }

}
