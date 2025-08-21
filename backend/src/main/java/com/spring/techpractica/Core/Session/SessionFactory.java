package com.spring.techpractica.Core.Session;

import com.spring.techpractica.Application.Session.CreateSession.CreateSessionCommand;
import com.spring.techpractica.Core.Session.Entity.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {

    public Session create(CreateSessionCommand command) {

        return Session.builder()
                .sessionName(command.name())
                .sessionDescription(command.description())
                .isPrivate(command.isPrivate())
                .sessionIsRunning(false)
                .build();
    }
}
