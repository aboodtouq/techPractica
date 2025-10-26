package com.spring.techpractica.application.session.get.by.id;

import com.spring.techpractica.core.Session.Entity.Session;
import com.spring.techpractica.core.Session.SessionRepository;
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