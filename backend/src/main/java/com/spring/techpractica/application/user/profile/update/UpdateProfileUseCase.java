package com.spring.techpractica.application.user.profile.update;

import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.SocialAccount.Entity.SocialAccount;
import com.spring.techpractica.core.SocialAccount.SocialAccountFactory;
import com.spring.techpractica.core.SocialAccount.model.PlatformName;
import com.spring.techpractica.core.SocialAccount.model.SocialAccountRequest;
import com.spring.techpractica.core.Technology.Entity.Technology;
import com.spring.techpractica.core.Technology.TechnologyRepository;
import com.spring.techpractica.core.User.User;
import com.spring.techpractica.core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class UpdateProfileUseCase {

    private final UserRepository userRepository;
    private final TechnologyRepository technologyRepository;
    private final SocialAccountFactory socialAccountFactory;

    @Transactional
    public User execute(UpdateProfileCommand command) {
        User user = userRepository.getOrThrowByID(command.userId());

        Set<Technology> technologies =
                new HashSet<>(technologyRepository.findAllByIds(command.skillsIds()));

        if (technologies.size() != command.skillsIds().size()) {
            throw new ResourcesNotFoundException(command.skillsIds().toString());
        }

        List<SocialAccountRequest> requests = command.socialAccountRequests();

        Map<PlatformName, String> requestByPlatform = new LinkedHashMap<>();

        for (SocialAccountRequest req : requests) {
            requestByPlatform.put(req.platformName(), req.profileUrl());
        }

        List<SocialAccount> existingAccounts = user.getSocialAccounts();
        List<SocialAccount> mergedAccounts = new ArrayList<>();

        for (SocialAccount account : existingAccounts) {
            PlatformName platform = account.getId().getPlatformName();
            if (requestByPlatform.containsKey(platform)) {
                account.setProfileUrl(requestByPlatform.get(platform));
                requestByPlatform.remove(platform);
                mergedAccounts.add(account);
            }
        }

        requestByPlatform.forEach((platform, url) -> {
            SocialAccount created = socialAccountFactory.create(platform, url, user);
            created.setUser(user);
            mergedAccounts.add(created);
        });

        user.setSocialAccounts(mergedAccounts);

        user.setSkills(technologies);

        user.addInfo(command.firstName(), command.lastName(), command.brief());

        return userRepository.update(user);
    }
}