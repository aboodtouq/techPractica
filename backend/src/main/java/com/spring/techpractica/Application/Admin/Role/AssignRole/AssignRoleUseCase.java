package com.spring.techpractica.Application.Admin.Role.AssignRole;

import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.Core.Role.RoleRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssignRoleUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public String execute(AssignRoleCommand command){
        User user = userRepository.getOrThrowByID(command.id());


        List<Role> roles = command.roles().stream()
                .map(roleType -> roleRepository.findByRoleType(roleType)
                        .orElseThrow(() -> new ResourcesNotFoundException("Role not found: " + roleType)))
                .toList();

        user.updateRoles(roles);

        userRepository.update(user);

        return "Role Assigned";
    }
}