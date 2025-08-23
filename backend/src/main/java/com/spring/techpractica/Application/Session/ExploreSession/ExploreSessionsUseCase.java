package com.spring.techpractica.Application.Session.ExploreSession;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExploreSessionsUseCase {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Session> execute(ExploreSessionsCommand command) {
        UUID userId = command.userId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException(userId));

        if (user.getUserTechnologies().isEmpty()) {
            return sessionRepository.exploreSessions(PageRequest.of(command.number(), command.size()));
        }
        throw new UnsupportedOperationException("For User complete account set up soon  ");
    }
}
