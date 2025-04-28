package com.spring.techpractica.controller;

import com.spring.techpractica.dto.otp.NewPassword;
import com.spring.techpractica.dto.otp.OtpResponse;
import com.spring.techpractica.dto.otp.UserEmailSendOtp;
import com.spring.techpractica.dto.otp.UserSubmitOtp;
import com.spring.techpractica.dto.userRegestation.LoginResponse;
import com.spring.techpractica.dto.userRegestation.UserCreateAccount;
import com.spring.techpractica.dto.userRegestation.UserLogin;
import com.spring.techpractica.service.MailSenderService;
import com.spring.techpractica.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticated")
@Tag(
        name = "Authentication Controller",
        description = "Handles user authentication operations including registration, login, and password reset via OTP."
)
public class AuthenticatedController {

    private final UserService userService;

    private final MailSenderService mailSenderService;


    public AuthenticatedController(UserService userService,
                                   MailSenderService mailSenderService) {
        this.userService = userService;
        this.mailSenderService = mailSenderService;
    }

    @Operation(
            summary = "Create a new user account",
            description = "Allows a user to register by providing required account details like name, email, and password."
    )
    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody UserCreateAccount userCreateAccount) {
        userService.createAccount(userCreateAccount);
        return ResponseEntity.ok("Create Account Successful ");
    }

    @Operation(
            summary = "Login with email and password",
            description = "Authenticates the user with the provided credentials and returns a JWT token on success."
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLogin userLogin) {
        String token = userService.userLogin(userLogin);

        String email = userLogin.getUserEmail();
        LoginResponse response = new LoginResponse( email,token);
        return ResponseEntity.ok(response);

    }

    @Operation(
            summary = "Send password reset OTP",
            description = "Generates a one-time password (OTP) and sends it to the user's email to begin the password reset process."
    )
    @PostMapping("/send-reset-password")
    public ResponseEntity<OtpResponse> sendResetPassword(
            @RequestBody UserEmailSendOtp userEmailSendOtp) {

        OtpResponse otpResponse = userService.userCreateOtpCode(userEmailSendOtp);

        mailSenderService.sendResetPassword(otpResponse);

        return ResponseEntity.ok(otpResponse);
    }

    @Operation(
            summary = "Submit OTP for password reset",
            description = "Validates the submitted OTP and returns a temporary token for resetting the password."
    )
    @PostMapping("/submit-OTP")
    public ResponseEntity<String> submitOtp(@RequestBody UserSubmitOtp otpRequest) {
        String token = userService.userSubmitOtp(otpRequest);
        return ResponseEntity.ok(token);
    }

    @Operation(
            summary = "Submit a new password",
            description = "Allows an authenticated user to submit a new password. " +
                    "The user must be logged in, and the new password is provided in the request body."

    )   
    )

      @PostMapping("/submit-new-password")
      public ResponseEntity<String> submitNewPassword(@RequestBody NewPassword newPassword,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        userService.userChangePassword(userEmail, newPassword);
        return ResponseEntity.ok("Password changed. Please log in again.");
    }

}