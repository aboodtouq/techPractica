package com.spring.techpractica.infrastructure.Jpa.Role;

import com.spring.techpractica.Core.Role.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRole extends JpaRepository<Role, UUID> {
}