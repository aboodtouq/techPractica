package com.spring.techpractica.Application.User.Profile.GetProfile;

import com.spring.techpractica.Core.SocialAccount.model.SocialAccountRequest;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record GetProfileCommand(UUID userId) {

}
