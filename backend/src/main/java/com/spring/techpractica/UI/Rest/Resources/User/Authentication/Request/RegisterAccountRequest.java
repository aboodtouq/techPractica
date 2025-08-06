package com.spring.techpractica.UI.Rest.Request.User.Authentication.Request;

import jakarta.validation.constraints.Email;

public record RegisterAccountRequest(@Email String email, String password, String name) {
}
