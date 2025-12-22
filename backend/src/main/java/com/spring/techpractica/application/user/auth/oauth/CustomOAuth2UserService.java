package com.spring.techpractica.application.user.auth.oauth;

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
    private final GitHubEmailFetcher emailFetcher;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request)
            throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);
        Map<String, Object> attrs = oAuth2User.getAttributes();

        String name = (String) attrs.get("login");

        // 1️⃣ الإيميل من GitHub user object (غالبًا null)
        String email = (String) attrs.get("email");

        // 2️⃣ إذا null → نجيبه من GitHub API
        if (email == null) {
            String accessToken = request
                    .getAccessToken()
                    .getTokenValue();

            email = emailFetcher.fetchPrimaryEmailAddress(accessToken);
        }

        // 3️⃣ إذا لسا null (نادر)
        if (email == null) {
            email = name + "@github.local";
        }

        // 4️⃣ حفظ المستخدم
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);

            userRepository.save(user);

            System.out.println("✅ USER SAVED WITH REAL EMAIL: " + email);
        }

        return oAuth2User;
    }
}
