package com.spring.techpractica.application.user.Auth.RegisterAccount;

import com.spring.techpractica.Core.Role.Model.RoleType;
import com.spring.techpractica.Core.User.Event.UserRegistrationEvent;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserFactory;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegisterAccountUseCase {
    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public User execute(RegisterAccountCommand command) {
        String email = command.email();
        if (userRepository.existsByEmail(email)) {
            return userFactory.create(command.name(), email, command.password(), RoleType.ROLE_USER);
        }
        User user = userFactory.create(command.name(), email, command.password(), RoleType.ROLE_USER);
        User userSaved = userRepository.save(user);
        publisher.publishEvent(new UserRegistrationEvent(userSaved.getId(), userSaved.getEmail(), userSaved.getName()));
        return userSaved;
    }
}
