package com.spring.techpractica.Application.Session.CreateSession;

import com.spring.techpractica.Core.Field.Entity.Field;
import com.spring.techpractica.Core.Field.Entity.FieldRepository;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionFactory;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.SessionMembers.Entity.SessionMember;
import com.spring.techpractica.Core.SessionMembers.SessionMembersFactory;
import com.spring.techpractica.Core.SessionMembers.model.Role;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.System.Entity.System;
import com.spring.techpractica.Core.System.Entity.SystemRepository;
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

    private final SystemRepository systemRepository;
    private final FieldRepository fieldRepository;

    @Transactional
    public Session execute(CreateSessionCommand command) {
        User owner = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourcesNotFoundException(command.userId()));

        Session session = sessionFactory.create(command);

        SessionMember sessionMember = sessionMembersFactory
                .createSessionMembers(session,owner, Role.OWNER);

        session.addMember(sessionMember);

        System system =systemRepository.findSystemByName(command.system())
                        .orElseThrow(() -> new ResourcesNotFoundException(command.system()));
        session.addSystem(system);

        Field field =fieldRepository.findSystemByName(command.field())
                .orElseThrow(() -> new ResourcesNotFoundException(command.field()));
        session.addSystem(field);

        return sessionRepository.save(session);
    }
}