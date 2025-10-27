package com.spring.techpractica.ui.rest.controller.User.Auth.ResetPassword;

import jakarta.validation.constraints.Email;

public record ResetPasswordRequest(@Email String email) {
}
