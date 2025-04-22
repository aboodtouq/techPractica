package com.spring.techpractica.service;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.model.entity.*;
import com.spring.techpractica.repository.SessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public SessionResponse createSession(SessionCreatorRequest creatorRequest, String userEmail) {

        User userOwner = getUserByEmail(userEmail);

        Session createdSession = createSessionFromRequest(creatorRequest);

        setUserSessionRole(createdSession, userOwner, "OWNER");

        addSessionRequirements(createdSession, creatorRequest.getFields());

        addCategoryToSession(createdSession, creatorRequest.getCategory());


        addTechnologiesToSession(createdSession, creatorRequest.getTechnologies());


        sessionRepository.save(createdSession);


        return sessionMapper.sessionToSessionResponse(createdSession);
    }

    private void setUserSessionRole(Session session, User user, String role) {
        SessionMemberRelationShip sessionRole = SessionMemberRelationShip.builder()
                .user(user)
                .session(session)
                .scopedRole(role)
                .build();

        session.getSessionMembers().add(sessionRole);
    }


    private User getUserByEmail(String userEmail) {
        return userService.findUserByUserEmail(userEmail)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));
    }

    private Session createSessionFromRequest(SessionCreatorRequest creatorRequest) {
        return sessionMapper.sessionCreatorToSession(creatorRequest);
    }

    private void addSessionRequirements(Session createdSession, List<String> fields) {
        createdSession.setSessionRequests(new ArrayList<>());
        fields.forEach(field -> {
            createdSession.getSessionRequirements()
                    .add(createRequirementForField(field, createdSession));
        });
    }

    private Requirement createRequirementForField(String fieldName, Session session) {

        Field field = fieldService.findFieldByFieldName(fieldName)
                .orElseThrow(() -> new ResourcesNotFoundException("Field not found"));
        return Requirement.builder()
                .field(field)
                .session(session)
                .build();
    }

    private void addCategoryToSession(Session createdSession, String categoryName) {
        Category category = categoryService.findCategoryByName(categoryName);
        createdSession.getSessionCategories().add(category);
    }

    private void addTechnologiesToSession(Session createdSession, List<String> technologies) {

        List<Technology> techList = technologies.stream()
                .map(tech -> technologyService.findTechnologyByName(tech)
                        .orElseThrow(() -> new ResourcesNotFoundException("Tech not found")))
                .collect(Collectors.toList());

        createdSession.setSessionTechnologies(techList);
    }

    public List<SessionResponse> getSessionsByUserEmail(String userEmail, int pageSize, int pageNumber) {

        User user = userService.findUserByUserEmail(userEmail).orElseThrow(() ->
                new ResourcesNotFoundException("User not found")
        );

        if (user.getUserTechnologies() == null || user.getUserTechnologies().isEmpty()) {

            if (pageNumber < 0 || pageSize <= 0) {
                throw new ResourcesNotFoundException("Page number or Size is negative");
            }

            Pageable sessionPage = PageRequest.of(pageNumber, pageSize);
            Page<Session> page = sessionRepository.findAll(sessionPage);
            return page.map(sessionMapper::sessionToSessionResponse).stream().toList();
        }
        return null;
    }

    public List<SessionResponse> getSessionsByCategoryName(String categoryName, int pageSize, int pageNumber) {

        Category category = categoryService.findCategoryByName(categoryName);


        if (pageNumber < 0 || pageSize <= 0) {
            throw new ResourcesNotFoundException("Page number or Size is negative");
        }

        Pageable sessionPage = PageRequest.of(pageNumber, pageSize);
        Page<Session> page = sessionRepository.findAllBySessionCategories(category, sessionPage);


        return page.map(sessionMapper::sessionToSessionResponse).stream().toList();
    }
}
