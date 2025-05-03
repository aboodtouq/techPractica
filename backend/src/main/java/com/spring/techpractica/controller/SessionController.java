package com.spring.techpractica.controller;

import com.spring.techpractica.dto.session.SessionRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.dto.session.SessionsResponse;
import com.spring.techpractica.service.session.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/sessions")
@Tag(
        name = "Sessions Controller",
        description = "Handles user Session operations including create Session ....."
)
public class SessionController {


    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Operation(
            summary = "Create a new Session",
            description = "Allows a user to create a new Session by providing required session details like name, description, and many data fielis"
    )
    @PostMapping("/")
    public ResponseEntity<SessionResponse> createSession(
            @RequestBody SessionRequest sessionRequest
            , @AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();

        return ResponseEntity.ok(sessionService.createSession(sessionRequest,
                userEmail));
    }


    @Operation(
            summary = "Get Available Sessions",
            description = "Retrieves a paginated list of available sessions for the authenticated user using page size and page number."
    )
    @GetMapping("/")
    public ResponseEntity<SessionsResponse> getSessions(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam int pageSize,
            @RequestParam int pageNumber) {

        String userEmail = userDetails.getUsername();


        return ResponseEntity.ok(sessionService.
                getSessionsByUserEmail(userEmail, pageSize, pageNumber));
    }

    @Operation(
            summary = "Get Sessions by Category",
            description = "Fetches a paginated list of sessions based on the provided category name."
    )
    @GetMapping("/category")
    public ResponseEntity<SessionsResponse> getSessionsByCategoryName(
            @RequestParam String categoryName,
            @RequestParam int pageSize, @RequestParam int pageNumber) {
        return ResponseEntity.ok(sessionService.
                getSessionsByCategoryName(categoryName, pageSize, pageNumber));
    }

    @Operation(
            summary = "Get User Sessions ",
            description = "Retrieves a paginated list of the user sessions using page size and page number."
    )
    @GetMapping("/users")
    public ResponseEntity<SessionsResponse> getUserSessions(@AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestParam int pageSize, @RequestParam int pageNumber) {
        String userEmail = userDetails.getUsername();
        return ResponseEntity.ok(sessionService.
                getUserSessions(userEmail, pageSize, pageNumber));


    }

    @Operation(
            summary = "Edit a Session",
            description = "Updates the session details for the given session ID. Requires the user's authentication."
    )
    @PutMapping("/{sessionId}")
    public ResponseEntity<SessionResponse> editSession(
            @PathVariable Long sessionId,
            @RequestBody SessionRequest updatedSessionRequest,
            @AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();

        return ResponseEntity.ok(sessionService.
                updateSession(sessionId, updatedSessionRequest, userEmail));

    }


    @Operation(
            summary = "Delete a Session",
            description = "Deletes the session identified by the given session ID. Requires user authentication."
    )

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<String> deleteSession(
            @PathVariable Long sessionId,
            @AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();

        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<SessionsResponse> getSessions(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return ResponseEntity.ok(sessionService.getSessions(pageSize,pageNumber));
    }

}
