package com.spring.techpractica.controller;

import com.spring.techpractica.dto.restpassword.OtpRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordResponse;
import com.spring.techpractica.dto.user.UserCreateAccount;
import com.spring.techpractica.dto.user.UserLogin;
import com.spring.techpractica.service.JwtService;
import com.spring.techpractica.service.MailSenderService;
import com.spring.techpractica.service.ResetPasswordService;
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

    private final ResetPasswordService resetPasswordService;

    private final JwtService jwtService;

    public AuthenticatedController(UserService userService, MailSenderService mailSenderService, ResetPasswordService resetPasswordService, JwtService jwtService) {
        this.userService = userService;
        this.mailSenderService = mailSenderService;
        this.resetPasswordService = resetPasswordService;
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
    public ResponseEntity<ResetPasswordResponse> sendResetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {

        ResetPasswordResponse resetPasswordResponse = userService.userCreateOtpCode(resetPasswordRequest);

        mailSenderService.sendResetPassword(resetPasswordResponse);

        return ResponseEntity.ok(resetPasswordResponse);
    }

    @PostMapping("/submit-OTP")
    public ResponseEntity<String> submitOtp(@RequestBody OtpRequest otpRequest) {
        String token = userService.userSubmitOtp(otpRequest);
        return ResponseEntity.ok(token);
    }

}