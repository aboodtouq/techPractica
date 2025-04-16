package com.spring.techpractica.service;

import com.spring.techpractica.dto.otp.NewPassword;
import com.spring.techpractica.dto.otp.OtpResponse;
import com.spring.techpractica.dto.otp.UserSendOtp;
import com.spring.techpractica.dto.otp.UserSubmitOtp;
import com.spring.techpractica.dto.user.UserCreateAccount;
import com.spring.techpractica.dto.user.UserLogin;
import com.spring.techpractica.exception.AuthenticationException;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.maper.UserMapper;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final OtpService resetPasswordService;

    private final UserMapper userMapper;

    public UserService(JwtService jwtService, UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Lazy OtpService resetPasswordService,
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

    @Transactional
    public String userLogin(UserLogin userLogin) {

        User user = userRepository.findUserByUserEmail(userLogin.getUserEmail())
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));

        if (!passwordEncoder.matches(userLogin.getUserPassword(), user.getUserPassword())) {
            throw new AuthenticationException("Wrong password");
        }
        return jwtService.generateToken(user.getUserEmail());
    }

    public OtpResponse userCreateOtpCode(UserSendOtp userSendOtp) {
        return resetPasswordService.createResetPassword(userSendOtp);
    }

    public String userSubmitOtp(UserSubmitOtp userSubmitOtp) {
        resetPasswordService.validationOtp(userSubmitOtp);
        return jwtService.generateToken(userSubmitOtp.getUserEmail());
    }

    public Optional<User> findUserByUserEmail(String userEmail) {
        return userRepository.findUserByUserEmail(userEmail);
    }

    public void userChangePassword(String userEmail,
                                   NewPassword newPassword) {

        User user = findUserByUserEmail(userEmail).orElseThrow(() -> new ResourcesNotFoundException("User not found"));
        if (!newPassword.getPassword().equals(user.getUserPassword())) {
            throw new AuthenticationException("Wrong password");
        }

        user.setUserPassword(passwordEncoder.encode(newPassword.getPassword()));
    }

}
