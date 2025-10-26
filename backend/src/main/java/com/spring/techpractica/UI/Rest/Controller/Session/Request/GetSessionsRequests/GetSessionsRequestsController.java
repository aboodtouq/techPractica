package com.spring.techpractica.UI.Rest.Controller.Session.Request.GetSessionsRequests;

import com.spring.techpractica.application.session.request.get.sessions.requests.GetSessionsRequestsCommand;
import com.spring.techpractica.application.session.request.get.sessions.requests.GetSessionsRequestsUseCase;
import com.spring.techpractica.Core.Request.Entity.Request;
import com.spring.techpractica.Core.User.UserAuthentication;
import com.spring.techpractica.UI.Rest.Resources.Request.RequestCollection;
import com.spring.techpractica.UI.Rest.Resources.Request.RequestResources;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sessions/requests")
@AllArgsConstructor
@Tag(name = "Session", description = "Operations related to session requirements and requests")
public class GetSessionsRequestsController {

    private final GetSessionsRequestsUseCase getSessionsRequestsUseCase;

    @Operation(
            summary = "return requests for a specific session",
            description = "retuern the requests for a specific session. Requires authentication."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Request returned successfully",
                    content = @Content(schema = @Schema(implementation = RequestResources.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resources not found",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))
            )
    })
    @GetMapping("/{sessionId}/")
    public ResponseEntity<?> getSessionRequests(@AuthenticationPrincipal UserAuthentication userAuthentication
            , @PathVariable("sessionId") UUID sessionId) {

        List<Request> requests = getSessionsRequestsUseCase.execute(
                new GetSessionsRequestsCommand(
                        userAuthentication.getUserId(),
                        sessionId
                )
        );


        RequestCollection responseData = new RequestCollection(requests);

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        StandardSuccessResponse.<RequestCollection>builder()
                                .data(responseData)
                                .message("Requests returned successfully")
                                .status(HttpStatus.OK.value())
                                .build()
                );
    }
}
