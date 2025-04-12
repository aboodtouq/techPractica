package com.spring.techpractica.service;

import com.spring.techpractica.dto.*;
import com.spring.techpractica.exception.AuthenticationException;
import com.spring.techpractica.model.entity.ResetPassword;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResetPasswordService resetPasswordService;
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ResetPasswordService resetPasswordService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.resetPasswordService = resetPasswordService;
    }

    //createAccount
    //HashingPassword
    //check email is exist
    //check username is exist
    //save User
    @Transactional
    public void createAccount(UserCreateAccount userCreateAccount) {

        userRepository.findUserByUserEmail(userCreateAccount.getUserEmail())
                .ifPresent(user -> {
                    throw new AuthenticationException("Email is already in use");
                });

        userRepository.findUserByUserName(userCreateAccount.getName())
                .ifPresent(user -> {
                    throw new AuthenticationException("User name is already in use");
                });



        String encodedPassword = passwordEncoder.encode(userCreateAccount.getUserPassword());


        User user = User.builder()
                .userName(userCreateAccount.getName())
                .userFirstName(userCreateAccount.getFirstName())
                .userLastName(userCreateAccount.getLastName())
                .userEmail(userCreateAccount.getUserEmail())
                .userPassword(encodedPassword).build();

        userRepository.save(user);
    }

    //LOGIN
    public void userLogin(UserLogin userLogin) {
    //getOrElse

        User user = userRepository.findUserByUserEmail(userLogin.getUserEmail())
                .orElseThrow(() -> new AuthenticationException("User not found"));

        if (!passwordEncoder.matches(userLogin.getUserPassword(), user.getUserPassword())){
            throw new AuthenticationException("Wrong password");
        }
    }

    public ResetPasswordResponse userCreateResetPassword(ResetPasswordRequest resetPasswordRequest) {
        return resetPasswordService.createResetPassword(resetPasswordRequest);
    }

    public void userSubmitOtp(OtpRequest otpRequest) {
        resetPasswordService.submitOtp(otpRequest);
    }
}
