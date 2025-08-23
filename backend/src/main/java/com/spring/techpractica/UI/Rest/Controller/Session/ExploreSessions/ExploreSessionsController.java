package com.spring.techpractica.UI.Rest.Controller.Session.ExploreSessions;

import com.spring.techpractica.Application.Session.ExploreSession.ExploreSessionsCommand;
import com.spring.techpractica.Application.Session.ExploreSession.ExploreSessionsUseCase;
import com.spring.techpractica.Core.User.UserAuthentication;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionCollection;
import com.spring.techpractica.UI.Rest.Shared.Exception.InvalidPageRequestException;
import com.spring.techpractica.UI.Rest.Shared.StandardErrorResponse;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/session")
@AllArgsConstructor
@Tag(name = "Explore Sessions", description = "Operations for exploring available sessions")
public class ExploreSessionsController {

    private final ExploreSessionsUseCase exploreSessionsUseCase;

    @Operation(summary = "Explore sessions", description = "Retrieves a paginated list of sessions for exploration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = SessionCollection.class))),
            @ApiResponse(responseCode = "400", description = "Invalid page or size parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized access",
                    content = @Content),
            @ApiResponse(
                    responseCode = "501", description = "Operation not supported",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))
            )})
    @GetMapping("/explore")
    public ResponseEntity<?> exploreSessions(
            @AuthenticationPrincipal UserAuthentication authentication,
            @RequestParam int size,
            @RequestParam int page) {

        if (page < 0 || size < 1) {
            throw new InvalidPageRequestException(page, size);
        }

        try {
            SessionCollection response = new SessionCollection(
                    exploreSessionsUseCase.execute(
                            new ExploreSessionsCommand(authentication.getUserId(), page, size)
                    )
            );

            return ResponseEntity.ok(StandardSuccessResponse.<SessionCollection>builder()
                    .data(response)
                    .message("Explore sessions successfully executed")
                    .status(HttpStatus.OK.value())
                    .build());
        } catch (UnsupportedOperationException ex) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                    .body(StandardErrorResponse.builder()
                            .timestamp(Instant.now())
                            .message(ex.getMessage() != null ? ex.getMessage() : "Operation not supported")
                            .status(HttpStatus.NOT_IMPLEMENTED.value())
                            .code("NOT_IMPLEMENTED")
                            .build());
        }
    }
}
