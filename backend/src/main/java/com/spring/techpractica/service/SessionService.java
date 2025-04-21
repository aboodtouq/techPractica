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

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final SessionMapper sessionMapper;

    private final UserService userService;

    private final FieldService fieldService;

    private final TechnologyService technologyService;

    private final CategoryService categoryService;

    public SessionService(SessionRepository sessionRepository,
                          SessionMapper sessionMapper, SessionMapper sessionMapper1, UserService userService, FieldService fieldService, TechnologyService technologyService, CategoryService categoryService) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper1;
        this.userService = userService;
        this.fieldService = fieldService;
        this.technologyService = technologyService;
        this.categoryService = categoryService;
    }

    @Transactional
    public SessionResponse createSession(SessionCreatorRequest creatorRequest,
                                         String userEmail) {

        User userOwner = userService.findUserByUserEmail(userEmail).orElseThrow(() ->
                new ResourcesNotFoundException("User not found")
        );

        Session createdSession =
                sessionMapper.sessionCreatorToSession(creatorRequest);

        setUserSessionRole(createdSession, userOwner, "OWNER");


        creatorRequest
                .getFields()
                .forEach(field -> {
                    createdSession.setSessionRequests(new ArrayList<>());
                    createdSession.getSessionRequirements().add(Requirement.builder()
                            .field(fieldService.findFieldByFieldName(field).orElseThrow(() -> new ResourcesNotFoundException("Field not found")))
                            .session(createdSession)
                            .build());
                });

        createdSession.getSessionCategories().add(categoryService.findCategoryByName(creatorRequest.getCategory()));

        createdSession.setSessionTechnologies(creatorRequest
                .getTechnologies()
                .stream()
                .map((tech) -> technologyService.findTechnologyByName(tech)
                        .orElseThrow(() -> new ResourcesNotFoundException("Tech not found"))).toList());

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
