package com.spring.techpractica.core.User;

import com.spring.techpractica.core.Role.Entity.Role;
import com.spring.techpractica.core.Role.Model.RoleType;
import com.spring.techpractica.core.Role.RoleRepository;
import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.User.Service.PasswordEncryptor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserFactory {

    private final PasswordEncryptor passwordEncryptor;
    private final RoleRepository roleRepository;

    public User create(String name,
                       String email,
                       String password,
                       RoleType role) {
        String encryptedPassword = passwordEncryptor.hash(password);

        Role userRole = roleRepository.findByRoleType(role)
                .orElseThrow(() -> new ResourcesNotFoundException(role.toString()));

        return User.builder()
                .name(name)
                .email(email)
                .password(encryptedPassword)
                .roles(List.of(userRole))
                .build();
    }
}
