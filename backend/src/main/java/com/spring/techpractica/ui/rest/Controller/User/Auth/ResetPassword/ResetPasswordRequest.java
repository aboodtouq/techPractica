package com.spring.techpractica.ui.rest.Controller.User.Auth.ResetPassword;

import jakarta.validation.constraints.Email;

public record ResetPasswordRequest(@Email String email) {
}
