package com.spring.techpractica.mengmentData;

import com.spring.techpractica.model.entity.AuthenticatedUserSession;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.repository.AuthenticatedUserSessionRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticatedUserSessionManagementData {
    private final AuthenticatedUserSessioneRepository authenticatedUserSessioneRepository;

    public AuthenticatedUserSessionManagementData(
            AuthenticatedUserSessioneRepository authenticatedUserSessioneRepository) {
        this.authenticatedUserSessioneRepository = authenticatedUserSessioneRepository;

    }


    public List<Session> getUserSessionsByPageable(User user, Pageable pageable) {
        List<AuthenticatedUserSession> authenticatedUserSessions = authenticatedUserSessioneRepository.findAllByUser(user, pageable).getContent();
        return authenticatedUserSessions.stream().map
                ((session) -> session.getSession()).collect(Collectors.toList());
    }


    private final AuthenticatedUserSessionRepository authenticatedUserSessionRepository;

    public long getNumberOfUserSessions(User user) {
        return authenticatedUserSessioneRepository.countByUser(user);

        public Optional<AuthenticatedUserSession> findByUserUserIdAndUserSessionId (Long userId, Long sessionId){
            return authenticatedUserSessionRepository
                    .findByUserUserIdAndSessionSessionId(userId, sessionId);
        }
        public Optional<AuthenticatedUserSession> findByUserUserIdAndUserSessionId (Long userId, Long sessionId){
            return authenticatedUserSessionRepository
                    .findByUserUserIdAndSessionSessionId(userId, sessionId);


        }


    }
}