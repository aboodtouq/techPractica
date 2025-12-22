package com.spring.techpractica.infrastructure.security.oauth.config;

import com.spring.techpractica.core.shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.user.User;
import com.spring.techpractica.core.user.UserRepository;
import com.spring.techpractica.infrastructure.jwt.JwtGeneration;
import com.spring.techpractica.infrastructure.security.oauth.GitHubEmailFetcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtGeneration jwtGeneration;
    private final UserRepository userRepository;
    private final GitHubEmailFetcher emailFetcher;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2AuthenticationToken token =
                (OAuth2AuthenticationToken) authentication;

        String email = token.getPrincipal().getAttribute("email");

        if (email == null) {
            email = emailFetcher.fetchPrimaryEmail(token.toString());
        }

        String finalEmail = email;
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourcesNotFoundException(finalEmail));

        String jwt = jwtGeneration.generateLoginToken(
                user.getId(), user.getEmail(), user.getRoles()
        );

        System.out.println("jwt: " + jwt);

        response.sendRedirect(
                "http://localhost:3000/oauth2/success?token=" + jwt
        );
    }

}