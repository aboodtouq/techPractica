package com.spring.techpractica.Core.Role;

import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.Core.Role.Model.RoleType;
import com.spring.techpractica.Core.Shared.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role> {
    Optional<Role> findByRoleType(RoleType roleType);

    List<Role> findAll();
}
