package com.spring.techpractica.ui.rest.Controller.User.Auth.ChangePassword;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(@NotBlank String password, @NotBlank String confirmPassword) {
}
