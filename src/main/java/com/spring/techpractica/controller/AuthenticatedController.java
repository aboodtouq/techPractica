package com.spring.techpractica.controller;

import com.spring.techpractica.dto.*;
import com.spring.techpractica.service.MailSender;
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

    private final MailSender mailSender;

    public AuthenticatedController(UserService userService, MailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody UserCreateAccount userCreateAccount) {
        userService.createAccount(userCreateAccount);
        return ResponseEntity.ok("Create Account Successful ");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        userService.userLogin(userLogin);
        return ResponseEntity.ok("Login Successful ");
    }

    /*
    Abood
     */
    @PostMapping("/send-reset-password")
    public ResponseEntity<ResetPasswordResponse> sendResetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        ResetPasswordResponse resetPassword = userService.userCreateResetPassword(resetPasswordRequest);
        mailSender.sendResetPassword(resetPassword);
        return ResponseEntity.ok(resetPassword);
    }

    /*
     Qandeel
     */
    @PostMapping("/submit-OTP")
    public ResponseEntity<String> submitOtp(@RequestBody OtpRequest otpRequest) {

        return null;
    }
}
