package com.spring.techpractica.factory;

import com.spring.techpractica.dto.userRegestation.UserCreateAccount;
import com.spring.techpractica.maper.UserMapper;
import com.spring.techpractica.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFactory {

    private final PasswordEncoder passwordEncoder;



    public User createFrom(UserCreateAccount dto) {
        String encodedPassword = passwordEncoder.encode(dto.getUserPassword());
        User user = UserMapper.userCreateAccountToUser(dto);
        user.setUserPassword(encodedPassword);
        return user;
    }
}
