package com.spring.techpractica.UI.Rest.Controller.User.Auth;

import com.spring.techpractica.Application.User.LoginAccount.LoginAccountCommand;
import com.spring.techpractica.Application.User.LoginAccount.LoginAccountUseCase;
import com.spring.techpractica.Application.User.RegisterAccount.RegisterAccountCommand;
import com.spring.techpractica.Application.User.RegisterAccount.RegisterAccountUseCase;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.UI.Rest.Resources.User.Authentication.Request.LoginAccountRequest;
import com.spring.techpractica.UI.Rest.Resources.User.Authentication.Request.RegisterAccountRequest;
import com.spring.techpractica.UI.Rest.Resources.User.Authentication.Response.LoginAccountResponse;
import com.spring.techpractica.UI.Rest.Resources.User.UserResources;
import com.spring.techpractica.UI.Rest.Shared.StandardResponse;
import com.spring.techpractica.infrastructure.Jwt.JwtGeneration;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class UserAuthenticationController {
    private final RegisterAccountUseCase registerAccountUseCase;
    private final LoginAccountUseCase loginAccountUseCase;
    private final JwtGeneration jwtGeneration;

    @PostMapping("/register")
    public ResponseEntity<StandardResponse<UserResources>> registerAccount(@RequestBody @Valid RegisterAccountRequest request) {
        User user = registerAccountUseCase.execute(new RegisterAccountCommand(request.name(), request.email(), request.password()));

        return ResponseEntity.ok(StandardResponse.<UserResources>builder()
                .data(new UserResources(user))
                .message("Register account successful")
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse<LoginAccountResponse>> loginAccount(@RequestBody @Valid LoginAccountRequest request) {
        User user = loginAccountUseCase.execute(new LoginAccountCommand(request.email(), request.password()));
        String token = jwtGeneration.generateToken(user.getId(), user.getName());

        return ResponseEntity.ok(StandardResponse.<LoginAccountResponse>builder()
                .data(new LoginAccountResponse(user, token))
                .message("Login account successful")
                .build());
    }
}
