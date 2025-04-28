package com.spring.techpractica.validator.otpValidator;

import com.spring.techpractica.exception.AuthenticationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OtpValidatorExpiration extends OtpValidator<LocalDateTime> {

    @Override
    public void validate(LocalDateTime expireDate) {
        if (expireDate.isBefore(LocalDateTime.now())) {
            throw new AuthenticationException("Expired Expiration Date");
        }
    }
}
