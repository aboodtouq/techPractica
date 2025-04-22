package com.spring.techpractica.controller;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/sessions")
public class SessionController {


    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/")
    public ResponseEntity<SessionResponse> createSession(
            @RequestBody SessionCreatorRequest sessionCreatorRequest
            , @AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();

        return ResponseEntity.ok(sessionService.createSession(sessionCreatorRequest,
                userEmail));
    }


}
