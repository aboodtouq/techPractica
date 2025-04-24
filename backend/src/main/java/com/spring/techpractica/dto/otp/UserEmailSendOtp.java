package com.spring.techpractica.dto.otp;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEmailSendOtp {

    @NonNull
    private String userEmail;

}