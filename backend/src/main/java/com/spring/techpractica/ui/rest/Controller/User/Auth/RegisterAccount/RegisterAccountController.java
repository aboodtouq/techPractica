package com.spring.techpractica.ui.rest.Controller.User.Auth.RegisterAccount;

import com.spring.techpractica.application.user.auth.register.RegisterAccountCommand;
import com.spring.techpractica.application.user.auth.register.RegisterAccountUseCase;
import com.spring.techpractica.core.user.User;
import com.spring.techpractica.ui.rest.Resources.User.UserResources;
import com.spring.techpractica.ui.rest.Shared.StandardSuccessResponse;
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
public class RegisterAccountController {

    private final RegisterAccountUseCase registerAccountUseCase;

    @Operation(summary = "Register a new user account", description = "Creates a new user account with name, email, and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registration successful.Please check your email to verify your account",
                    content = @Content(schema = @Schema(implementation = UserResources.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request payload",
                    content = @Content),
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody @Valid RegisterAccountRequest request) {
        User user = registerAccountUseCase.execute(new RegisterAccountCommand(request.name(),
                request.email(),
                request.password()));

        StandardSuccessResponse<UserResources> response = StandardSuccessResponse.<UserResources>builder()
                .data(new UserResources(user))
                .message("Registration successful.Please check your email to verify your account")
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
