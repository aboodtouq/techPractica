package com.spring.techpractica.dto.otp;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPassword {

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

}
