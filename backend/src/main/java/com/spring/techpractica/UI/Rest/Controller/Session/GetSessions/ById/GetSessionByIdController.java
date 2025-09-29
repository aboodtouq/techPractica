package com.spring.techpractica.UI.Rest.Controller.Session.GetSessions.ById;

import com.spring.techpractica.Application.Session.GetSessions.ById.GetSessionByIdUseCase;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.User.UserAuthentication;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionCollection;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionResources;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
@Tag(name = "Session")
public class GetSessionById {

    private final GetSessionByIdUseCase  getSessionByIdUseCase;

    @GetMapping("/{sessionId}")
    public ResponseEntity<?> getSessionById(@RequestParam UUID sessionId,
                                            @AuthenticationPrincipal UserAuthentication userAuthentication) {

        Session session = getSessionByIdUseCase.execute(sessionId);

        SessionResources responseData = new SessionResources(session);

        return ResponseEntity.ok(StandardSuccessResponse.<SessionResources>builder()
                .data(responseData)
                .message("Get sessions by ID successfully executed")
                .status(HttpStatus.OK.value())
                .build());    }
}