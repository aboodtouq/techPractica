package com.spring.techpractica.ui.rest.controller.User.Auth.ChangePassword;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(@NotBlank String password, @NotBlank String confirmPassword) {
}
