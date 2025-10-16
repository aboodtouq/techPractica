package com.spring.techpractica.Application.Admin.Role.AssignRole;

import com.spring.techpractica.Core.Role.Model.RoleType;

import java.util.List;
import java.util.UUID;

public record AssignRoleCommand(UUID id, List<UUID> roleIds) {
}
