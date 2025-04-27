package com.spring.techpractica.factory;

import com.spring.techpractica.mengmentData.AuthenticatedUserSessionManagementData;
import com.spring.techpractica.model.SessionRole;
import com.spring.techpractica.model.UserSessionId;
import com.spring.techpractica.model.entity.AuthenticatedUserSession;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserSessionFactory {
    private final AuthenticatedUserSessionManagementData authenticatedUserSessionManagementData;


    public AuthenticatedUserSessionFactory(AuthenticatedUserSessionManagementData authenticatedUserSessionManagementData) {
        this.authenticatedUserSessionManagementData = authenticatedUserSessionManagementData;
    }


    public AuthenticatedUserSession createAuthenticatedUserSession(Session session,
                                                                          User user,
                                                                          SessionRole role) {
        return AuthenticatedUserSession.builder()
                .userSessionId(new UserSessionId())
                .user(user)
                .session(session)
                .scopedRole(role)
                .build();


    }

}
