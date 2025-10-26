package com.spring.techpractica.application.session.create;

import com.spring.techpractica.core.Session.Entity.Session;
import com.spring.techpractica.core.Session.SessionFactory;
import com.spring.techpractica.core.Session.SessionRepository;
import com.spring.techpractica.core.Session.service.AddRequirementsForSessionService;
import com.spring.techpractica.core.SessionMembers.Entity.SessionMember;
import com.spring.techpractica.core.SessionMembers.SessionMembersFactory;
import com.spring.techpractica.core.SessionMembers.model.Role;
import com.spring.techpractica.core.System.Entity.System;
import com.spring.techpractica.core.System.SystemRepository;
import com.spring.techpractica.core.User.User;
import com.spring.techpractica.core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateSessionUseCase {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SessionFactory sessionFactory;
    private final SessionMembersFactory sessionMembersFactory;
    private final SystemRepository systemRepository;
    private final AddRequirementsForSessionService requirementsForSession;

    @Transactional
    public Session execute(CreateSessionCommand command) {
        User owner = userRepository.getOrThrowByID(command.userId());

        Session session = sessionFactory.create(command);
        session = sessionRepository.save(session);

        addOwner(session, owner);
        session.addBasicInfo(session.getName(), session.getDescription(), session.isPrivate());
        addSystem(session, command.system());

        requirementsForSession.addRequirementsForSession(session,command);

        return sessionRepository.save(session);
    }

    private void addOwner(Session session, User owner) {
        SessionMember sessionMember = sessionMembersFactory.create(session, owner, Role.OWNER);
        session.addMember(sessionMember);
    }

    private void addSystem(Session session, UUID systemId) {
        System system = systemRepository.getOrThrowByID(systemId);
        session.addSystem(system);
    }
}