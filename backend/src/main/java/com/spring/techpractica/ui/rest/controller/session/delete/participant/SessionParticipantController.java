package com.spring.techpractica.ui.rest.controller.session.delete.participant;

import com.spring.techpractica.core.user.UserAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/session/")
public class SessionParticipantController {

    @DeleteMapping("/{sessionId}/participants/{participantId}")
    public ResponseEntity<?> deleteSessionParticipant(@AuthenticationPrincipal UserAuthentication  user,
                                                      @PathVariable UUID sessionId,
                                                      @PathVariable UUID participantId) {
        UUID ownerId = user.getUserId();

    }
}