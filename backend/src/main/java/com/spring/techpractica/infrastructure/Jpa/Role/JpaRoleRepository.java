package com.spring.techpractica.infrastructure.Jpa.Role;

import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.Core.Role.RoleRepository;

import java.util.Optional;
import java.util.UUID;

public class JpaRoleRepository implements RoleRepository {
    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return Optional.empty();
    }
}
