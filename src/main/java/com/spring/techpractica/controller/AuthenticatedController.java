package com.spring.techpractica.controller;

import com.spring.techpractica.dto.otp.NewPassword;
import com.spring.techpractica.dto.otp.OtpResponse;
import com.spring.techpractica.dto.otp.UserSendOtp;
import com.spring.techpractica.dto.otp.UserSubmitOtp;
import com.spring.techpractica.dto.user.UserCreateAccount;
import com.spring.techpractica.dto.user.UserLogin;
import com.spring.techpractica.service.MailSenderService;
import com.spring.techpractica.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/api/v1/authenticated")
public class AuthenticatedController {

    private final UserService userService;

    private final MailSenderService mailSenderService;


    public AuthenticatedController(UserService userService,
                                   MailSenderService mailSenderService) {
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

    @PostMapping("/submit-new-password")
    public ResponseEntity<String> submitNewPassword(NewPassword newPassword,
                                                    @AuthenticationPrincipal UserPrincipal userPrincipal) {

        String userEmail = userPrincipal.getName();
        userService.userChangePassword(userEmail, newPassword);
        return ResponseEntity.ok("Password changed. Please log in again.");
    }

}