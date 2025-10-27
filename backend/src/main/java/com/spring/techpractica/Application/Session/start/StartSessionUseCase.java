package com.spring.techpractica.Application.Session.start;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Session.SessionStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StartSessionUseCase {

    private final SessionRepository sessionRepository;

    public Session execute(StartSessionCommand command) {
        Session session = sessionRepository.getOrThrowByID(command.sessionId());

        session.runSession();


    }
}