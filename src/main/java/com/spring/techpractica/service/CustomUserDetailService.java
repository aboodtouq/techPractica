package com.spring.techpractica.service;

import com.spring.techpractica.exception.AuthenticationException;
import com.spring.techpractica.model.CustomUserDetails;
import com.spring.techpractica.model.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByUserEmail(email);
        if (user.isPresent()) {
            return user
                    .map(CustomUserDetails::new)
                    .get();
        }
        throw new AuthenticationException(email);
    }
}

