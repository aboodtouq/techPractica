package com.spring.techpractica.Application.Session.delete;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Session.SessionStatus;
import com.spring.techpractica.Core.Shared.Exception.UnauthorizedActionException;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
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