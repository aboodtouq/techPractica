package com.spring.techpractica.mengmentData;

import com.spring.techpractica.model.entity.AuthenticatedUserSession;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticatedUserSessionManagementData {
    private final AuthenticatedUserSessioneRepository authenticatedUserSessioneRepository;

    public AuthenticatedUserSessionManagementData(
            AuthenticatedUserSessioneRepository authenticatedUserSessioneRepository) {
        this.authenticatedUserSessioneRepository = authenticatedUserSessioneRepository;

    }

    /*
    Get authentication specif users  by pages find
     */

    public List<Session> getUserSessionsByPageable(User user, Pageable pageable) {
        List<AuthenticatedUserSession> authenticatedUserSessions= authenticatedUserSessioneRepository.findAllByUser(user,pageable).getContent();
       return authenticatedUserSessions.stream().map
                ((session)-> session.getSession()).collect(Collectors.toList());
    }

//    private

    private final AuthenticatedUserSessionRepository authenticatedUserSessionRepository;
    //fitch getAll count authtication specif users (COUNT)
    public long getNumberOfUserSessions(User user) {
        return  authenticatedUserSessioneRepository.countByUser(user);

    public Optional<AuthenticatedUserSession> findByUserUserIdAndUserSessionId(Long userId, long sessionId) {
        return authenticatedUserSessionRepository
                .findByUserUserIdAndUserSessionId(userId, sessionId);
    }
}