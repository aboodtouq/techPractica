package com.spring.techpractica.UI.Rest.Resources.SessionMember;

import com.spring.techpractica.Core.SessionMembers.model.Role;
import com.spring.techpractica.UI.Rest.Resources.User.UserResources;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SessionMemberResources {

    private UUID id;
    private UserResources user;
    private Role role;

}
