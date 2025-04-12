package com.spring.techpractica.dto.restpassword;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequest {
    @NonNull
    private String userEmail;
}