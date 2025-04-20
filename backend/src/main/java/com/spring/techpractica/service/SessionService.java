package com.spring.techpractica.service;


import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;




import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.model.TimestampType;
import com.spring.techpractica.model.entity.Requirement;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.Timestamp;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.model.entity.Skill;
import com.spring.techpractica.repository.RequirementRepository;
import com.spring.techpractica.repository.SessionRepository;
import com.spring.techpractica.repository.TimestampRepository;
import com.spring.techpractica.repository.UserRepository;
import com.spring.techpractica.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final TimestampRepository timestampRepository;
    private final RequirementRepository requirementRepository;
    private final UserRepository userRepository;
    private final SessionMapper sessionMapper;

    public SessionService(SessionRepository sessionRepository,
                          TimestampRepository timestampRepository,
                          RequirementRepository requirementRepository,
                          UserRepository userRepository,

                          SessionMapper sessionMapper) {
        this.sessionRepository    = sessionRepository;
        this.timestampRepository  = timestampRepository;
        this.requirementRepository = requirementRepository;
        this.userRepository       = userRepository;
        this.sessionMapper        = sessionMapper;
    }

    /*
          find User by email
          Create Session and put information from creator request
          Create timeStamp for Session type Created
          Create Requirement
          return SessionResponse
    */
    @Transactional
    public SessionResponse createSession(SessionCreatorRequest creatorRequest,
                                         String userEmail) {


        User user = userRepository
                .findUserByUserEmail(userEmail)
                .orElseThrow(() ->
                        new ResourcesNotFoundException("User not found with email: ")
                );


        Session session = sessionMapper
                .sessionCreatorToSession(creatorRequest);
        //should the session has a owner data filed?




        Timestamp ts = Timestamp.builder()
                .eventType(TimestampType.CREATED)
                .eventDate(LocalDate.now())
                .build();
        session.setTimestampList(ts);
        //why the timestamp should be a list?
        timestampRepository.save(ts);

        for (Requirement requirement : creatorRequest.getRequirements()) {
            requirement.builder().session(session).skillType(requirement.getSkillType());
            requirementRepository.save(requirement);

        }



        return SessionResponse.builder()
                .build();
    }
}
