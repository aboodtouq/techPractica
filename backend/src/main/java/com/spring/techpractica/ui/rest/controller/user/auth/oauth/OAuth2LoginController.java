package com.spring.techpractica.ui.rest.controller.user.auth.oauth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class OAuth2LoginController {

    @GetMapping("/github")
    public String loginWithGithub() {
        return "redirect:/oauth2/authorization/github";
    }
}