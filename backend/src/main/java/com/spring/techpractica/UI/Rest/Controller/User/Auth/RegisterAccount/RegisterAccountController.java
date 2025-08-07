package com.spring.techpractica.UI.Rest.Controller.User.Auth.RegisterAccount;

import com.spring.techpractica.Application.User.RegisterAccount.RegisterAccountCommand;
import com.spring.techpractica.Application.User.RegisterAccount.RegisterAccountUseCase;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.UI.Rest.Resources.User.UserResources;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
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

@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Tag(name = "Authentication")
public class RegisterAccountController {
    private final RegisterAccountUseCase registerAccountUseCase;

    @Operation(summary = "Register a new user account", description = "Creates a new user account with name, email, and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered",
                    content = @Content(schema = @Schema(implementation = UserResources.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request payload",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Email already in use",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<StandardSuccessResponse<UserResources>> registerAccount(@RequestBody @Valid RegisterAccountRequest request) {
        User user = registerAccountUseCase.execute(new RegisterAccountCommand(request.name(), request.email(), request.password()));

        StandardSuccessResponse<UserResources> response = StandardSuccessResponse.<UserResources>builder()
                .data(new UserResources(user))
                .message("Register account successful")
                .status(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
