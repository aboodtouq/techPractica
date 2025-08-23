package com.spring.techpractica.UI.Rest.Controller.Session.ExploreSessions;

import com.spring.techpractica.Application.Session.ExploreSession.ExploreSessionsCommand;
import com.spring.techpractica.Application.Session.ExploreSession.ExploreSessionsUseCase;
import com.spring.techpractica.Core.User.UserAuthentication;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionCollection;
import com.spring.techpractica.UI.Rest.Shared.Exception.InvalidPageRequestException;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session/explore")
@AllArgsConstructor
public class ExploreSessionsController {

    private final ExploreSessionsUseCase exploreSessionsUseCase;

    @GetMapping
    public ResponseEntity<?> exploreSessions(@AuthenticationPrincipal UserAuthentication authentication,
                                             @RequestParam int size, @RequestParam int page) {
        if (page < 0 || size < 1) {
            throw new InvalidPageRequestException(page, size);
        }

        SessionCollection response =
                new SessionCollection(exploreSessionsUseCase.execute
                        (new ExploreSessionsCommand(authentication.getUserId(), page, size)));

        return ResponseEntity.ok()
                .body(StandardSuccessResponse.<SessionCollection>builder()
                        .data(response)
                        .message("Explore sessions successfully executed")
                        .status(HttpStatus.OK.value())
                        .build());
    }
}
