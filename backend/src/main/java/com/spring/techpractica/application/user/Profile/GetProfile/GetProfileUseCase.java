package com.spring.techpractica.application.user.Profile.GetProfile;

import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class GetProfileUseCase {

    private final UserRepository userRepository;

    @Transactional
    public User execute(GetProfileCommand command) {
        User user = userRepository.getOrThrowByID(command.userId());

        if (!user.isProfileComplete()) {
            throw new UnsupportedOperationException(
                    "user profile is not implemented yet"
            );
        }

        return user;
    }
}
