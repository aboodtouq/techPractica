package com.spring.techpractica.Core.User;

import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.Core.Role.Model.RoleType;
import com.spring.techpractica.Core.Role.RoleRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.User.Service.PasswordEncryptor;
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
                       String password) {
        String encryptedPassword = passwordEncryptor.hash(password);

        Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
                .orElseThrow(() -> new ResourcesNotFoundException(RoleType.ROLE_USER.toString()));

        return User.builder()
                .name(name)
                .email(email)
                .password(encryptedPassword)
                .roles(List.of(userRole))
                .build();
    }
}
