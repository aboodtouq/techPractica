package com.spring.techpractica.service;

import com.spring.techpractica.dto.UserCreateAccount;
import com.spring.techpractica.dto.UserLogin;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //createAccount
    //HashingPassword
    //check email is exist
    //check username is exist
    //save User
    public void createAccount(UserCreateAccount userCreateAccount) {

        if(userRepository.existsByEmail(  userCreateAccount.getUserEmail()  ) ) {

            throw new IllegalArgumentException("the email already exists");
        }

        if (userRepository.existsByUsername(  userCreateAccount.getName()  )){
            throw new IllegalArgumentException("the username already exists");
        }


        String encodedPassword = passwordEncoder.encode(userCreateAccount.getUserPassword());


        User user =User.builder()
                .userName(userCreateAccount.getName())
                .userFirstName(userCreateAccount.getFirstName())
                .userLastName(userCreateAccount.getLastName())
                .userEmail(userCreateAccount.getUserEmail())
                        .userPassword(encodedPassword).build();

        userRepository.save(user);
    }

    //LOGIN
    //cheak
    public void userLogin(UserLogin userLogin) {
        // تنفيذ منطق تسجيل الدخول هنا
    }
}
