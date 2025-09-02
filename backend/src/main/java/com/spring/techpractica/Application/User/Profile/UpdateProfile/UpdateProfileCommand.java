package com.spring.techpractica.Application.User.Profile.UpdateProfile;

import com.spring.techpractica.Core.SocialAccount.model.SocialAccountRequest;

import java.util.List;
import java.util.UUID;

public record UpdateProfileCommand(UUID userId
        , String firstName
        , String lastName
        , String brief
        , List<UUID> skillsIds
        , List<SocialAccountRequest> socialAccountRequests) {

}
