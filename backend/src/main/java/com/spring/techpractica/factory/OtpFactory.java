package com.spring.techpractica.factory;

import com.spring.techpractica.model.entity.Otp;
import com.spring.techpractica.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OtpFactory {

    private OtpFactory() {

    }

    public static Otp createOtpFrom(User user) {
        return Otp.builder()
                .user(user)
                .build();
    }

}
