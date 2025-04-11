package com.spring.techpractica.service;

import com.spring.techpractica.dto.UserCreateAccount;
import com.spring.techpractica.dto.UserLogin;
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
    //save User
    public void createAccount(UserCreateAccount userCreateAccount) {

    }

    //LOGIN
    //cheak
    public void userLogin(UserLogin userLogin) {

    }

}
