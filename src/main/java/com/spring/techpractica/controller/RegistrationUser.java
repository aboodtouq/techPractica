package com.spring.techpractica.controller;

import com.spring.techpractica.dto.UserCreateAccount;
import com.spring.techpractica.dto.UserLogin;
import com.spring.techpractica.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationUser {

    private final UserService userService;

    public RegistrationUser(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-accaount")
    public void createAccount(@ModelAttribute UserCreateAccount userCreateAccount) {
        userService.createAccount(userCreateAccount);
    }

    @PostMapping("/login")
    public void login(@ModelAttribute UserLogin userLogin) {
        userService.userLogin(userLogin);
    }
}
