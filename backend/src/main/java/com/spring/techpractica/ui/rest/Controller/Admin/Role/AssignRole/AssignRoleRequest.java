package com.spring.techpractica.ui.rest.Controller.Admin.Role.AssignRole;

import java.util.List;
import java.util.UUID;

public record AssignRoleRequest(UUID id, List<UUID> roleIds) {
}
