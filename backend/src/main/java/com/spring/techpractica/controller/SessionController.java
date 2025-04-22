package com.spring.techpractica.controller;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestBody SessionCreatorRequest sessionCreatorRequest
            , @AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();

        return ResponseEntity.ok(sessionService.createSession(sessionCreatorRequest,
                userEmail));
    }


    @Operation(
            summary = "Create a new Session",
            description = "Allows a user to create a new Session by providing required session details like name, description, and many data fielis"
    )
    @GetMapping("/")
    public ResponseEntity<List<SessionResponse>> getSessions(
            @AuthenticationPrincipal UserDetails userDetails, @RequestParam int  pageSize,@RequestParam int pageNumber) {

        String userEmail = userDetails.getUsername();


        return ResponseEntity.ok(sessionService.getSessions(userEmail,pageSize,pageNumber));
    }
}
