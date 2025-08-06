package com.spring.techpractica.Core.User;

import com.spring.techpractica.Core.User.Service.PasswordEncryption;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    private PasswordEncryption passwordEncryption;

    public User create(String name,
                       String firstName,
                       String lastName,
                       String email,
                       String password) {
        String encryptedPassword = passwordEncryption.encryptPassword(password);

        return User.builder()
                .name(name)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(encryptedPassword)
                .build();
    }
}
