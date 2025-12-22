package com.spring.techpractica.ui.rest.controller;

import com.spring.techpractica.application.GitHubRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/github")
public class GitHubController {

    private final GitHubRepositoryService gitHubRepositoryService;

    @PostMapping("/repo")
    public ResponseEntity<?> createRepo(
            @RequestParam String name,
            Authentication authentication
    ) {
        gitHubRepositoryService.createRepository(authentication, name);
        return ResponseEntity.ok("Repository created successfully");
    }
}
