package com.spring.techpractica.ui.rest.Controller.User.Auth.RegisterAccount;

import jakarta.validation.constraints.Email;

public record RegisterAccountRequest(@Email String email, String password, String name) {
}
