package com.spring.techpractica.application.user.profile.update;

import com.spring.techpractica.Core.SocialAccount.model.SocialAccountRequest;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record UpdateProfileCommand(UUID userId
        , String firstName
        , String lastName
        , String brief
        , Set<UUID> skillsIds
        , List<SocialAccountRequest> socialAccountRequests) {

}
