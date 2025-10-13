package com.spring.techpractica.UI.Rest.Controller.Admin.Role.AssignRole;

import com.spring.techpractica.Core.Role.Model.RoleType;

import java.util.List;
import java.util.UUID;

public record AssignRoleRequest(UUID id, List<RoleType> roles) {
}
