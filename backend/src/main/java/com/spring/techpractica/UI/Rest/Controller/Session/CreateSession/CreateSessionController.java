package com.spring.techpractica.UI.Rest.Controller.Session.CreateSession;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session")
public class CreateSessionController {

    @PostMapping("/")
    public ResponseEntity<?> createSession(@RequestBody @Valid CreateSessionRequest request,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok("");
    }
}