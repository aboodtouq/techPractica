package com.spring.techpractica.UI.Rest.Resources.User.Authentication.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginAccountRequest(@Email String email, @NotBlank String password) {
}
