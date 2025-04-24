package com.spring.techpractica.factory;

import com.spring.techpractica.model.SessionRole;
import com.spring.techpractica.model.entity.AuthenticatedUserSession;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserSessionFactory {

    public AuthenticatedUserSession createAuthenticatedUserSession(Session session,
                                                                   User user,
                                                                   SessionRole role) {
        return AuthenticatedUserSession.builder()
                .user(user)
                .session(session)
                .scopedRole(role)
                .build();
    }

}
