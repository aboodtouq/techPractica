package com.spring.techpractica.service;

import com.spring.techpractica.dto.restpassword.OtpRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordResponse;
import com.spring.techpractica.dto.user.UserCreateAccount;
import com.spring.techpractica.dto.user.UserLogin;
import com.spring.techpractica.exception.AuthenticationException;
import com.spring.techpractica.maper.UserMapper;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ResetPasswordService resetPasswordService;

    private final UserMapper userMapper;

    public UserService(JwtService jwtService, UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Lazy ResetPasswordService resetPasswordService,
                       UserMapper userMapper) {
        this.jwtService = jwtService;

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.resetPasswordService = resetPasswordService;
        this.userMapper = userMapper;
    }

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

        User user = userMapper.userCreateAccountToUser(userCreateAccount);
        user.setUserPassword(encodedPassword);


        userRepository.save(user);
    }


    public String userLogin(UserLogin userLogin) {
        //getOrElse

        User user = userRepository.findUserByUserEmail(userLogin.getUserEmail())
                .orElseThrow(() -> new AuthenticationException("User not found"));

        if (!passwordEncoder.matches(userLogin.getUserPassword(), user.getUserPassword())) {
            throw new AuthenticationException("Wrong password");
        }
        return jwtService.generateToken(user.getUserEmail());
    }

    public ResetPasswordResponse userCreateOtpCode(ResetPasswordRequest resetPasswordRequest) {
        return resetPasswordService.createResetPassword(resetPasswordRequest);
    }

    public String userSubmitOtp(OtpRequest otpRequest) {
        resetPasswordService.validationOtp(otpRequest);
        return jwtService.generateToken(otpRequest.getUserEmail());
    }

    public Optional<User> findUserByUserEmail(String userEmail) {
        return userRepository.findUserByUserEmail(userEmail);
    }
}
