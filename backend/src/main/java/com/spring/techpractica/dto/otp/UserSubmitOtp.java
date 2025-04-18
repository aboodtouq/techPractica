package com.spring.techpractica.dto.otp;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSubmitOtp{
    private Long otpId;

    @Size(min = 6, max = 6)
    private String otp;

    private String userEmail;
}
