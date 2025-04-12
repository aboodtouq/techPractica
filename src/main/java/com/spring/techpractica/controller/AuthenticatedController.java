package com.spring.techpractica.controller;

import com.spring.techpractica.dto.restpassword.OtpRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordRequest;
import com.spring.techpractica.dto.restpassword.ResetPasswordResponse;
import com.spring.techpractica.dto.user.UserCreateAccount;
import com.spring.techpractica.dto.user.UserLogin;
import com.spring.techpractica.service.MailSenderService;
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

    public AuthenticatedController(UserService userService, MailSenderService mailSenderService) {
        this.userService = userService;
        this.mailSenderService = mailSenderService;
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

        ResetPasswordResponse resetPasswordResponse =
                userService.userCreateResetPassword(resetPasswordRequest);

        mailSenderService.sendResetPassword(resetPasswordResponse);

        return ResponseEntity.ok(resetPasswordResponse);
    }

    /*
     Qandeel
     */
    @PostMapping("/submit-OTP")
    public ResponseEntity<String> submitOtp(@RequestBody OtpRequest otpRequest) {

        return null;
    }
}