package com.spring.techpractica.UI.Rest.Resources.User;

import com.spring.techpractica.Core.Shared.BaseEntity;
import com.spring.techpractica.Core.SocialAccount.Entity.SocialAccount;
import com.spring.techpractica.Core.SocialAccount.model.SocialAccountId;
import com.spring.techpractica.Core.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UserResources {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String name;
    private final String email;
    private final Set<UUID> skillsIds;
    private final List<SocialAccountId> socialAccounts;
    private final String brief;


    public UserResources(User user) {
        this.id = user.getId().toString();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.name = user.getName();
        this.email = user.getEmail();
        this.skillsIds = user.getSkills().stream().map(BaseEntity::getId).collect(Collectors.toSet());
        this.socialAccounts = user.getSocialAccounts().stream().map(SocialAccount::getId).collect(Collectors.toList());
        this.brief = user.getBrief();
    }
}