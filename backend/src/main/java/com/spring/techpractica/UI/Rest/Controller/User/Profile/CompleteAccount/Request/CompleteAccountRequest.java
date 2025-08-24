package com.spring.techpractica.UI.Rest.Controller.User.Profile.CompleteAccount.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import jakarta.validation.Valid;

public record CompleteAccountRequest(
        @Size(min = 3) @NotBlank String firstName,
        @Size(min = 3) @NotBlank String lastName,
        String brief,
        @Size(min = 5) List<@NotBlank String> skillsNames,
        @Size(min = 1, max = 4) List<@Valid SocialAccountRequest> socialAccountRequests
) {}

