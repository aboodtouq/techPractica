package com.spring.techpractica.ui.rest.controller.session.delete.participant;

import com.spring.techpractica.application.session.delete.participant.DeleteSessionParticipantCommand;
import com.spring.techpractica.application.session.delete.participant.SessionParticipantUseCase;
import com.spring.techpractica.core.user.UserAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/session/")
@AllArgsConstructor
public class SessionParticipantController {

    private final SessionParticipantUseCase sessionParticipantUseCase;

    @DeleteMapping("/{sessionId}/participants/{participantId}")
    public ResponseEntity<?> deleteSessionParticipant(@AuthenticationPrincipal UserAuthentication  user,
                                                      @PathVariable UUID sessionId,
                                                      @PathVariable UUID participantId) {
        UUID ownerId = user.getUserId();

        sessionParticipantUseCase.execute(new DeleteSessionParticipantCommand(ownerId, participantId, sessionId));

        return ResponseEntity.ok("Delete Session participant executed successfully")
                .status(HttpStatus.OK)
                .build();
    }
}