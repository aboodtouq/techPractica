package com.spring.techpractica.maper;

import com.spring.techpractica.dto.otp.OtpResponse;
import com.spring.techpractica.model.entity.Otp;

public class OtpMapper {

    private OtpMapper() {
    }

    public static OtpResponse mapOtp(Otp otp) {
        return OtpResponse.builder()
                .otpId(otp.getOtpId())
                .userEmail(otp.getUser().getUserEmail())
                .build();

    }
}
