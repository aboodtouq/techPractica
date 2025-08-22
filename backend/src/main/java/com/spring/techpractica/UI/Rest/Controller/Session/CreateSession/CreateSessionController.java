package com.spring.techpractica.UI.Rest.Controller.Session.CreateSession;

import com.spring.techpractica.Application.Session.CreateSession.CreateSessionCommand;
import com.spring.techpractica.Application.Session.CreateSession.CreateSessionUseCase;
import com.spring.techpractica.Core.Requirement.Model.RequirementRequest;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.User.UserAuthentication;
import com.spring.techpractica.UI.Rest.Resources.Requirment.RequirementCollection;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionResources;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
                request.requirements().stream().map(
                        requirementRequest -> new RequirementRequest(requirementRequest.getFieldName()
                                ,requirementRequest.getTechnologies())
                ).toList()
        ));

        SessionResources responseData = new SessionResources(session);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        StandardSuccessResponse.<SessionResources>builder()
                                .data(responseData)
                                .message("Session created successfully")
                                .status(HttpStatus.CREATED.value())
                                .build()
                );
    }
}