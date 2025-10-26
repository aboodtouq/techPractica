package com.spring.techpractica.core.SessionMembers;

import com.spring.techpractica.core.Session.Entity.Session;
import com.spring.techpractica.core.SessionMembers.Entity.SessionMember;
import com.spring.techpractica.core.SessionMembers.model.Role;
import com.spring.techpractica.core.SessionMembers.model.UserSessionId;
import com.spring.techpractica.core.User.User;
import org.springframework.stereotype.Component;

@Component
public class SessionMembersFactory {

    public SessionMember create(Session session, User owner, Role role) {
        return SessionMember.builder()
                .userSessionId(new UserSessionId(session.getId(), owner.getId()))
                .user(owner)
                .session(session)
                .role(role)
                .build();
    }
}
