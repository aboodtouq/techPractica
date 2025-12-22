package com.spring.techpractica.application.user.auth;

import com.spring.techpractica.core.user.User;
import com.spring.techpractica.core.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request)
            throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);
        Map<String, Object> attrs = oAuth2User.getAttributes();

        String name = (String) attrs.get("login");
        String email = (String) attrs.get("email");
        String avatar = (String) attrs.get("avatar_url");
        String provider = request.getClientRegistration().getRegistrationId();

        // ✅ حل مؤقت واضح
        if (email == null) {
            email = name + "@github.local";
        }

        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);

            userRepository.save(user);

            System.out.println("✅ USER SAVED: " + email);
        }

        return oAuth2User;
    }
}
