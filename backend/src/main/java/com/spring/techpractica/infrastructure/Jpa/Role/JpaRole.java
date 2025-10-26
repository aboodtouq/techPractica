package com.spring.techpractica.infrastructure.Jpa.Role;

import com.spring.techpractica.core.Role.Entity.Role;
import com.spring.techpractica.core.Role.Model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaRole extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleType(RoleType roleType);
}