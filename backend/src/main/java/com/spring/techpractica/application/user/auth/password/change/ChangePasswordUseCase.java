package com.spring.techpractica.application.user.auth.password.change;

import com.spring.techpractica.core.User.Service.UserService;
import com.spring.techpractica.core.User.User;
import com.spring.techpractica.core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ChangePasswordUseCase {
    private final UserRepository userRepository;

    private final UserService userService;

    @Transactional
    public User execute(ChangePasswordCommand command) {
        User user = userRepository.getOrThrowByID(command.id());

        userService.changePassword(user, command.password());
        userRepository.update(user);
        return user;
    }
}
