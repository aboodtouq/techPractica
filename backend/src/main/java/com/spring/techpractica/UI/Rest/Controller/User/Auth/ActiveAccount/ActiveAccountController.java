package com.spring.techpractica.UI.Rest.Controller.User.Auth.ActiveAccount;

import com.spring.techpractica.Application.User.AcvtiveAccount.ActiveAccountCommand;
import com.spring.techpractica.Application.User.AcvtiveAccount.ActiveAccountUseCase;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.UI.Rest.Resources.User.UserResources;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import com.spring.techpractica.infrastructure.Jwt.JwtExtracting;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class ActiveAccountController {

    private final JwtExtracting jwtExtracting;
    private final ActiveAccountUseCase useCase;

    @GetMapping("/active-account")
    public ResponseEntity<?> verifyToken(@RequestParam String token) {
        UUID id = jwtExtracting.extractId(token);
        User user = useCase.execute(new ActiveAccountCommand(id));

        return ResponseEntity.status(HttpStatus.ACCEPTED.value())
                .body(StandardSuccessResponse.builder()
                        .data(new UserResources(user))
                        .message("Active account has been verified")
                        .status(HttpStatus.ACCEPTED.value())
                        .build());
    }
}

