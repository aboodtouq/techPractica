package com.spring.techpractica.UI.Rest.Resources.Role;

import com.spring.techpractica.Core.Role.Entity.Role;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RoleResources {

    private final UUID id;
    private final String type;

    public RoleResources(Role role) {
        this.id = role.getId();
        this.type = role.getRoleType().toString();
    }
}