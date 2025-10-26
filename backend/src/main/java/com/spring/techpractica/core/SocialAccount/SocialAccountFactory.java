package com.spring.techpractica.core.SocialAccount;

import com.spring.techpractica.core.SocialAccount.Entity.SocialAccount;
import com.spring.techpractica.core.SocialAccount.model.PlatformName;
import com.spring.techpractica.core.SocialAccount.model.SocialAccountId;
import com.spring.techpractica.core.User.User;
import org.springframework.stereotype.Component;

@Component
public class SocialAccountFactory {

    public SocialAccount create(PlatformName platformName, String profileUrl, User user) {
        return SocialAccount.builder().id(SocialAccountId.builder()
                        .platformName(platformName)
                        .userId(user.getId())
                        .build())
                .profileUrl(profileUrl)
                .user(user)
                .build();
    }
}
