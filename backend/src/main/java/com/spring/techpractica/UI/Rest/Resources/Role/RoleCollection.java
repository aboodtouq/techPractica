package com.spring.techpractica.UI.Rest.Resources.Role;

import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.UI.Rest.Resources.User.UserResources;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RoleCollection {

    private final List<RoleResources> roleCollection;

    public RoleCollection(List<Role> roles) {
        this.roleCollection = roles.stream()
                .map(RoleResources::new)
                .collect(Collectors.toList());

    }
}
