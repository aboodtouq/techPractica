package com.spring.techpractica.service;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.model.entity.AuthenticatedUserSession;
import com.spring.techpractica.model.entity.Requirement;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final SessionMapper sessionMapper;

    private final UserService userService;

    public SessionService(SessionRepository sessionRepository,
                          SessionMapper sessionMapper, SessionMapper sessionMapper1, UserService userService) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper1;
        this.userService = userService;
    }

    @Transactional
    public SessionResponse createSession(SessionCreatorRequest creatorRequest,
                                         String userEmail) {

        User userOwner = userService.findUserByUserEmail(userEmail).orElseThrow(() ->
                new ResourcesNotFoundException("User not found")
        );

        Session createdSession =
                sessionMapper.sessionCreatorToSession(creatorRequest);


        //set Owner
        setUserSessionRole(createdSession, userOwner, "OWNER");

        // should check of fields is exists in database
        creatorRequest
                .getFields()
                .forEach(field -> {
                    createdSession.setSessionRequests(new ArrayList<>());
                    createdSession.getSessionRequirements().add(Requirement.builder()
                            .field(field)
                            .session(createdSession)
                            .build());
                });

        createdSession.setSessionTechnologies(creatorRequest.getTechnologies());

        sessionRepository.save(createdSession);

        return sessionMapper.sessionToSessionResponse(createdSession);
    }


    private void setUserSessionRole(Session createdSession, User userOwner, String scopedRole) {

        createdSession.setSessionMembers(new ArrayList<>());
        createdSession.getSessionMembers()
                .add(
                        AuthenticatedUserSession
                                .builder()
                                .user(userOwner)
                                .scopedRole(scopedRole)
                                .build());
    }
}
