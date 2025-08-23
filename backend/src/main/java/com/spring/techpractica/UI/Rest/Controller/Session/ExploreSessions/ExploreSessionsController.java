package com.spring.techpractica.UI.Rest.Controller.Session.ExploreSessions;

import com.spring.techpractica.Core.User.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/session/explore")
public class ExploreSessionsController {

    @GetMapping
    public ResponseEntity<?> exploreSessions(@AuthenticationPrincipal UserAuthentication authentication) {
        return ResponseEntity.ok().build();
    }
}
