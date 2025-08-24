package com.spring.techpractica.UI.Rest.Controller.Session.GetSessions;

import com.spring.techpractica.Application.Session.ExploreSession.ExploreSessionsCommand;
import com.spring.techpractica.Application.Session.GetSessions.GetSessionsBySystemCommand;
import com.spring.techpractica.Application.Session.GetSessions.GetSessionsBySystemUseCase;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionCollection;
import com.spring.techpractica.UI.Rest.Shared.Exception.InvalidPageRequestException;
import com.spring.techpractica.UI.Rest.Shared.StandardErrorResponse;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/session")
@AllArgsConstructor
public class GetSessionsBySystemController {

    private final GetSessionsBySystemUseCase getSessionsBySystemUseCase;

    @Operation(
            summary = "Get sessions by system",
            description = "Retrieves a paginated list of sessions filtered by" +
                    " a specific system name"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessions retrieved successfully",
                    content = @Content(schema = @Schema(implementation = SessionCollection.class))),
            @ApiResponse(responseCode = "400", description = "Invalid page or size parameters",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "System not found",
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "Operation not supported",
                    content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<?> getSessionsBySystem(@RequestParam String systemName,
                                                 @RequestParam int sizeOfPage,
                                                 @RequestParam int pageNumber) {
        if (pageNumber < 0 || sizeOfPage < 1) {
            throw new InvalidPageRequestException(pageNumber, sizeOfPage);
        }

        try {
            SessionCollection response = new SessionCollection(
                    getSessionsBySystemUseCase.execute(
                            new GetSessionsBySystemCommand(systemName, pageNumber, sizeOfPage)
                    )
            );

            return ResponseEntity.ok(StandardSuccessResponse.<SessionCollection>builder()
                    .data(response)
                    .message("Get sessions by system successfully executed")
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
