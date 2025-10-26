package com.spring.techpractica.application.session.GetSessions.ById;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetSessionByIdUseCase {

    private final SessionRepository sessionRepository;

    public Session execute(UUID sessionId) {
        return sessionRepository.getOrThrowByID(sessionId);
    }
}