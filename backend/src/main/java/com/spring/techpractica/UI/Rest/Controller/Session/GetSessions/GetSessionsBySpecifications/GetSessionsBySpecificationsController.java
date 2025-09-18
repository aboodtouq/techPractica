package com.spring.techpractica.UI.Rest.Controller.Session.GetSessions.GetSessionsBySpecifications;

import com.spring.techpractica.Application.Session.GetSessions.GetSessionBySpecifications.GetSessionsBySpecificationsCommand;
import com.spring.techpractica.Application.Session.GetSessions.GetSessionBySpecifications.GetSessionsBySpecificationsUseCase;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionCollection;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
@Tag(name = "Session")
public class GetSessionsBySpecificationsController {

    private final GetSessionsBySpecificationsUseCase useCase;

    @GetMapping("/spec")
    public ResponseEntity<?> getSessionsBySpecification(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String fieldName,
            @RequestParam(required = false) String sessionName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        var response = new SessionCollection(
                useCase.execute(new GetSessionsBySpecificationsCommand(sort, fieldName, sessionName, page, size))
        );

        return ResponseEntity.ok(StandardSuccessResponse.<SessionCollection>builder()
                .data(response)
                .message("Get sessions by specification successfully executed")
                .status(HttpStatus.OK.value())
                .build());
    }
}

