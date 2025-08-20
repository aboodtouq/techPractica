package com.spring.techpractica.UI.Rest.Controller.User.Auth.ActiveAccount;

import com.spring.techpractica.Application.User.AcvtiveAccount.ActiveAccountCommand;
import com.spring.techpractica.Application.User.AcvtiveAccount.ActiveAccountUseCase;
import com.spring.techpractica.infrastructure.Jwt.JwtExtracting;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class ActiveAccountController {
    private final JwtExtracting jwtExtracting;
    private final ActiveAccountUseCase useCase;

    @GetMapping("/active-account")
    public void verifyToken(@RequestParam String token,
                            HttpServletResponse response) throws IOException {
        UUID id = UUID.fromString(jwtExtracting.extractId(token));
        useCase.execute(new ActiveAccountCommand(id));
        try {
            response.sendRedirect("http://localhost:3000/User");
        } catch (Exception e) {
            response.sendRedirect("http//localhost:3030/not-found");
        }
    }
}

