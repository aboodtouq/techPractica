package com.spring.techpractica.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GitHubRepositoryService {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final RestClient restClient;
    private static final String CREATE_REPO_URL = "https://api.github.com/user/repos";

    public void createRepository(Authentication authentication, String repoName) {

        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient client =
                authorizedClientService.loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName()
                );

        String accessToken = client.getAccessToken().getTokenValue();

        restClient.post()
                .uri(CREATE_REPO_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of(
                        "name", repoName,
                        "private", false,
                        "auto_init", true
                ))
                .retrieve()
                .toBodilessEntity();
    }
}
