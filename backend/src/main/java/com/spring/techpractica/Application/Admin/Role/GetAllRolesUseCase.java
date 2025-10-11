package com.spring.techpractica.Application.Admin.Role;

import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.Core.Role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllRolesUseCase {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
}