package com.spring.techpractica.controller;

import com.spring.techpractica.dto.UserCreateAccount;
import com.spring.techpractica.dto.UserLogin;
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

    public AuthenticatedController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody UserCreateAccount userCreateAccount) {
        userService.createAccount(userCreateAccount);
        return ResponseEntity.ok("Create Account Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        userService.userLogin(userLogin);
        return ResponseEntity.ok("Login Successful");
    }
}
