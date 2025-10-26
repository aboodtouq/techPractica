package com.spring.techpractica.UI.Rest.Controller.Session.start;

import com.spring.techpractica.Core.User.UserAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sessions")
public class StartSessionController {

    @PutMapping("/start/{sessionId}")
    public ResponseEntity<?> invoke(@AuthenticationPrincipal UserAuthentication userAuthentication,
                                    @PathVariable UUID sessionId){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}