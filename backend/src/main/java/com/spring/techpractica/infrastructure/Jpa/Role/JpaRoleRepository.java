package com.spring.techpractica.infrastructure.Jpa.Role;

import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.Core.Role.Model.RoleType;
import com.spring.techpractica.Core.Role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class JpaRoleRepository implements RoleRepository {

    private final JpaRole jpaRole;

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


    @Override
    public Optional<Role> findByRoleType(RoleType roleType) {
        return jpaRole.findByRoleType(roleType);
    }
}
