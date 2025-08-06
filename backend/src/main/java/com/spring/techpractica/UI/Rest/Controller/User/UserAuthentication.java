package com.spring.techpractica.UI.Rest.Controller.User;

import com.spring.techpractica.Application.User.RegisterAccount.RegisterAccount;
import com.spring.techpractica.Application.User.RegisterAccount.RegisterAccountCommand;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.UI.Rest.Request.User.Authentication.RegisterAccountRequest;
import com.spring.techpractica.UI.Rest.Resources.User.UserResources;
import com.spring.techpractica.UI.Rest.shared.StandardResponse;
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
public class UserAuthentication {
    private final RegisterAccount registerAccount;

    @PostMapping("/register")
    public ResponseEntity<StandardResponse<UserResources>> registerAccount(@RequestBody @Valid RegisterAccountRequest request) {
        User user = registerAccount.execute(new RegisterAccountCommand(request.email(), request.password(), request.name()));

        return ResponseEntity.ok(StandardResponse.<UserResources>builder()
                .data(new UserResources(user))
                .state("successful").build());
    }
}
