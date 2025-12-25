package com.spring.techpractica.ui.rest.controller.session.get.user.sessions.statistics;

import com.spring.techpractica.application.session.get.sessions.statistics.GetSessionStatisticsCommand;
import com.spring.techpractica.application.session.get.sessions.statistics.GetSessionStatisticsUseCase;
import com.spring.techpractica.core.session.entity.statistics.SessionStatistics;
import com.spring.techpractica.core.user.UserAuthentication;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
@Tag(name = "Session")
public class GetSessionStatisticsController {

    private final GetSessionStatisticsUseCase getSessionStatisticsUseCase;

    @GetMapping("/statistics/{sessionId}")
    public ResponseEntity<?> getSessionStatistics(@AuthenticationPrincipal UserAuthentication user,
                                                  @PathVariable UUID sessionId) {
        SessionStatistics statistics = getSessionStatisticsUseCase.execute(
                new GetSessionStatisticsCommand(
                        user.getUserId(),
                        sessionId
                )
        );


    }
}