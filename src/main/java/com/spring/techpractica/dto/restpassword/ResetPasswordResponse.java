package com.spring.techpractica.dto.restpassword;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordResponse {

    private Long resetId;

    private String userEmail;
}