package com.spring.techpractica.service;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.repository.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final SessionMapper sessionMapper;

    public SessionService(SessionRepository sessionRepository,
                          SessionMapper sessionMapper, SessionMapper sessionMapper1) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper1;
    }


    /*
           find User by email
           Create Session and put information from creator request
           Create timeStamp for Session type Created
           Create Requirement
           return SessionResponse
     */
    public SessionResponse createSession(SessionCreatorRequest creatorRequest,
                                         String userEmail) {

        return null;
    }
}
