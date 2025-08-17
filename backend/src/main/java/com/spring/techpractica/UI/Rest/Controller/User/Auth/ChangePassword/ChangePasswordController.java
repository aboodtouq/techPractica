package com.spring.techpractica.UI.Rest.Controller.User.Auth.ChangePassword;

import com.spring.techpractica.Application.User.ChangePassword.ChangePasswordCommand;
import com.spring.techpractica.Application.User.ChangePassword.ChangePasswordUseCase;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserAuthentication;
import com.spring.techpractica.UI.Rest.Resources.User.UserResources;
import com.spring.techpractica.UI.Rest.Shared.StandardErrorResponse;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ChangePasswordController {
    private final ChangePasswordUseCase useCase;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request,
                                            @AuthenticationPrincipal UserAuthentication authentication) {
        UUID id = authentication.getUserId();
        String password = request.password();
        String confirmPassword = request.confirmPassword();

        if (!confirmPassword.equals(password)) {
            return ResponseEntity.badRequest().body(StandardErrorResponse
                    .builder()
                    .timestamp(Instant.now())
                    .message("Password does not match")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .code(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        User user = useCase.execute(new ChangePasswordCommand(id, password));
        return ResponseEntity.accepted()
                .body(StandardSuccessResponse.builder()
                        .data(new UserResources(user))
                        .status(HttpStatus.ACCEPTED.value())
                        .message("Change Password Successful"));
    }
}
