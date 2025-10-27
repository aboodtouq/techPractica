package com.spring.techpractica.ui.rest.controller.User.Auth.RegisterAccount;

import jakarta.validation.constraints.Email;

public record RegisterAccountRequest(@Email String email, String password, String name) {
}
