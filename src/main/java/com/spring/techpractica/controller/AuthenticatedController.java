package com.spring.techpractica.controller;

import com.spring.techpractica.dto.otp.OtpResponse;
import com.spring.techpractica.dto.otp.UserSendOtp;
import com.spring.techpractica.dto.otp.UserSubmitOtp;
import com.spring.techpractica.dto.user.UserCreateAccount;
import com.spring.techpractica.dto.user.UserLogin;
import com.spring.techpractica.service.JwtService;
import com.spring.techpractica.service.MailSenderService;
import com.spring.techpractica.service.OtpService;
import com.spring.techpractica.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticated")
public class AuthenticatedController {

    private final UserService userService;

    private final MailSenderService mailSenderService;

    private final OtpService otpService;

    private final JwtService jwtService;

    public AuthenticatedController(UserService userService, MailSenderService mailSenderService, OtpService otpService, JwtService jwtService) {
        this.userService = userService;
        this.mailSenderService = mailSenderService;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody UserCreateAccount userCreateAccount) {
        userService.createAccount(userCreateAccount);
        return ResponseEntity.ok("Create Account Successful ");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        String token = userService.userLogin(userLogin);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/send-reset-password")
    public ResponseEntity<OtpResponse> sendResetPassword(@RequestBody UserSendOtp userSendOtp) {
        OtpResponse otpResponse = userService.userCreateOtpCode(userSendOtp);
        mailSenderService.sendResetPassword(otpResponse);
        return ResponseEntity.ok(otpResponse);
    }

    @PostMapping("/submit-OTP")
    public ResponseEntity<String> submitOtp(@RequestBody UserSubmitOtp otpRequest) {
        String token = userService.userSubmitOtp(otpRequest);
        return ResponseEntity.ok(token);
    }
}