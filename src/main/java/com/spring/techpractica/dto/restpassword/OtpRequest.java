package com.spring.techpractica.dto.restpassword;

import jakarta.validation.constraints.Size;

public class OtpRequest {

    @Size(min=6,max=6)
    private String otp;

    private Long resetPasswordId;

}
