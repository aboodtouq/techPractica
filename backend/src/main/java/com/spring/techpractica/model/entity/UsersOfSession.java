package com.spring.techpractica.model.entity;

import com.spring.techpractica.model.UserSessionId;
import jakarta.persistence.*;

@Entity
@Table(name = "USERS_SESSION")
public class UsersOfSession {

    @EmbeddedId
    private UserSessionId userSessionId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id")
    private Session session;

    @Column(name = "user_role")
    private String userRole;
}
