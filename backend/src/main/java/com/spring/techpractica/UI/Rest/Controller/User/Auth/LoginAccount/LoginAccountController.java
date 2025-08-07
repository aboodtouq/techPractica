package com.spring.techpractica.UI.Rest.Controller.User.Auth.LoginAccount;

import com.spring.techpractica.Application.User.LoginAccount.LoginAccountCommand;
import com.spring.techpractica.Application.User.LoginAccount.LoginAccountUseCase;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import com.spring.techpractica.infrastructure.Jwt.JwtGeneration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Tag(name = "Authentication")
public class LoginAccountController {
    private final LoginAccountUseCase loginAccountUseCase;
    private final JwtGeneration jwtGeneration;

    @Operation(summary = "Login user account", description = "Authenticates user and returns a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(schema = @Schema(implementation = LoginAccountResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<StandardSuccessResponse<LoginAccountResponse>> loginAccount(@RequestBody @Valid LoginAccountRequest request) {
        User user = loginAccountUseCase.execute(new LoginAccountCommand(request.email(), request.password()));
        String token = jwtGeneration.generateToken(user.getId(), user.getName());

        return ResponseEntity.ok(StandardSuccessResponse.<LoginAccountResponse>builder()
                .data(new LoginAccountResponse(user, token))
                .message("Login account successful")
                .status(HttpStatus.OK.value())
                .build());
    }
}
