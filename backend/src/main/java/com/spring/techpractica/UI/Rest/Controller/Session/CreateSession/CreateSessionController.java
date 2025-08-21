package com.spring.techpractica.UI.Rest.Controller.Session.CreateSession;

import com.spring.techpractica.Application.Session.CreateSession.CreateSessionCommand;
import com.spring.techpractica.Application.Session.CreateSession.CreateSessionUseCase;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.User.UserAuthentication;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionResources;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session")
@AllArgsConstructor
public class CreateSessionController {

    private final CreateSessionUseCase createSessionUseCase;

    @PostMapping("/")
    public ResponseEntity<?> createSession(@RequestBody @Valid CreateSessionRequest request,
                                           @AuthenticationPrincipal UserAuthentication userAuthentication) {

        Session session = createSessionUseCase.execute(new CreateSessionCommand(
                userAuthentication.getUserId(),
                request.name(),
                request.description(),
                request.isPrivate(),
                request.system(),
                request.field(),
                request.technology()
        ));

        SessionResources responseData = SessionResources.builder()
                .id(session.getId())
                .name(session.getSessionName())
                .description(session.getSessionDescription())
                .isPrivate(session.isPrivate())
                .isRunning(session.isSessionIsRunning())
                .system(session.getSystems().toString())
                .build();
    }
}