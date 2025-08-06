package com.spring.techpractica.UI.Rest.Request.User.Authentication.Request;

import jakarta.validation.constraints.Email;

public record LoginAccountRequest(@Email String email, String password) {
}
