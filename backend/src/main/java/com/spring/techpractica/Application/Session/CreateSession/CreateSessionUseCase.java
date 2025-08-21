package com.spring.techpractica.Application.Session.CreateSession;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionFactory;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.SessionMembers.Entity.SessionMember;
import com.spring.techpractica.Core.SessionMembers.SessionMembersFactory;
import com.spring.techpractica.Core.SessionMembers.model.Role;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateSessionUseCase {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SessionFactory sessionFactory;
    private final SessionMembersFactory sessionMembersFactory;

    @Transactional
    public Session createSession(CreateSessionCommand command) {
        User owner = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourcesNotFoundException(command.userId()));

        Session session = sessionFactory.create(command);

        SessionMember sessionMember = sessionMembersFactory
                .createSessionMembers(session,owner, Role.OWNER);

        session.addMember(sessionMember);

        return sessionRepository.save(session);
    }
}