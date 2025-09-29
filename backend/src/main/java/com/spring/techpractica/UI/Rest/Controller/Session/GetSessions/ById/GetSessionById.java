package com.spring.techpractica.UI.Rest.Controller.Session.GetSessions.ById;

import com.spring.techpractica.Core.User.UserAuthentication;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
@Tag(name = "Session")
public class GetSessionById {

    @GetMapping("/{sessionId}")
    public ResponseEntity<?> getSessionById(@RequestParam UUID sessionId,
                                            @AuthenticationPrincipal UserAuthentication userAuthentication) {

    }
}