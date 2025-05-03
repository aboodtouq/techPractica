package com.spring.techpractica.dto.otp;

import lombok.*;

/**
 * A DTO class that holds both the OTP provided by the user
 * and the one stored in the system for comparison.
 */
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpVerificationInput {

    private String userOtp;
    private String storedOtp;

}
