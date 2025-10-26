package com.spring.techpractica.core.Role;

import com.spring.techpractica.core.Role.Entity.Role;
import com.spring.techpractica.core.Role.Model.RoleType;
import com.spring.techpractica.core.Shared.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends BaseRepository<Role> {
    Optional<Role> findByRoleType(RoleType roleType);

    List<Role> findAll();

    List<Role> findAllByIds(List<UUID> roleIds);
}
