package com.spring.techpractica.application.session.delete;

import com.spring.techpractica.core.Session.Entity.Session;
import com.spring.techpractica.core.Session.SessionRepository;
import com.spring.techpractica.core.Session.SessionStatus;
import com.spring.techpractica.core.Shared.Exception.UnauthorizedActionException;
import com.spring.techpractica.core.User.User;
import com.spring.techpractica.core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class DeleteSessionUseCase {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Session execute(DeleteSessionCommand command) {
        User user = userRepository.getOrThrowByID(command.userId());

        Session session = sessionRepository.getOrThrowByID(command.sessionId());

        validateSessionOwner(session, user);

        session.setStatus(SessionStatus.DELETED);

        return sessionRepository.save(session);
    }

    private void validateSessionOwner(Session session, User user) {
        boolean isOwner = session.isOwner(user.getId());

        if (!isOwner) {
            throw new UnauthorizedActionException("User must be the session owner to delete it");
        }
    }

}