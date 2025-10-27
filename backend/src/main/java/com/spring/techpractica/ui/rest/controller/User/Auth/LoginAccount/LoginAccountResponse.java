package com.spring.techpractica.ui.rest.controller.User.Auth.LoginAccount;

import com.spring.techpractica.core.user.User;
import com.spring.techpractica.ui.rest.Resources.User.UserResources;
import lombok.Getter;

@Getter
public class LoginAccountResponse {
    private final UserResources user;

    private final String token;

    public LoginAccountResponse(User user, String token) {
        this.user = new UserResources(user);
        this.token = token;
    }
}
