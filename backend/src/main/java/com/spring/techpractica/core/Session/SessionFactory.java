package com.spring.techpractica.core.Session;

import com.spring.techpractica.application.session.create.CreateSessionCommand;
import com.spring.techpractica.core.Session.Entity.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {

    public Session create(CreateSessionCommand command) {

        return Session.builder()
                .name(command.name())
                .description(command.description())
                .isPrivate(command.isPrivate())
                .status(SessionStatus.WAITING)
                .build();
    }
}
