package com.spring.techpractica.Application.User.Profile.CompleteAccount;

import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.SocialAccount.Entity.SocialAccount;
import com.spring.techpractica.Core.SocialAccount.SocialAccountRepository;
import com.spring.techpractica.Core.SocialAccount.model.PlatformName;
import com.spring.techpractica.Core.SocialAccount.SocialAccountFactory;
import com.spring.techpractica.Core.Technology.Entity.Technology;
import com.spring.techpractica.Core.Technology.TechnologyRepository;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompleteAccountUseCase {

    private final UserRepository userRepository;
    private final TechnologyRepository technologyRepository;
    private final SocialAccountFactory socialAccountFactory;
    private final SocialAccountRepository socialAccountRepository;

    @Transactional
    public void execute(CompleteAccountCommand command) {

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourcesNotFoundException(command.userId()));

        List<Technology> technologies = command.skillsNames().stream()
                .map(technologyName -> technologyRepository.findTechnologyByName(technologyName)
                        .orElseThrow(() -> new ResourcesNotFoundException(technologyName)))
                .collect(Collectors.toCollection(ArrayList::new));


        List<SocialAccount> socialAccounts = command.socialAccountRequests().stream().map(request -> {

            PlatformName platform;
            try {
                platform = PlatformName.valueOf(request.platformName().trim().toUpperCase());
            } catch (IllegalArgumentException ex) {

                throw new ResourcesNotFoundException("Invalid social platform: " + request.platformName());
            }

            SocialAccount created = socialAccountFactory.create(platform, request.profileUrl(), user);
            created.setUser(user);

            return created;
        }).collect(Collectors.toCollection(ArrayList::new));

        if (!socialAccounts.isEmpty()) {
            socialAccountRepository.saveAll(socialAccounts);
        }
        user.getUserTechnologies() .addAll(technologies);
        user.getUserTechnologies().addAll(technologies);
        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setBrief(command.brief());

        user.completed();
        userRepository.save(user);
    }
}
