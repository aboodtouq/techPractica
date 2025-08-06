package com.spring.techpractica.Core.User;

import com.spring.techpractica.Core.User.Service.PasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    private PasswordEncryptor passwordEncryptor;

    public User create(String name,
                       String email,
                       String password) {
        String encryptedPassword = passwordEncryptor.hash(password);

        return User.builder()
                .name(name)
                .email(email)
                .password(encryptedPassword)
                .build();
    }
}
