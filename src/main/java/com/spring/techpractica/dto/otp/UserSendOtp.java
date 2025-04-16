package com.spring.techpractica.dto.otp;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSendOtp {

    @NonNull
    private String userEmail;

}