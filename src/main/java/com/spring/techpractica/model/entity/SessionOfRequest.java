package com.spring.techpractica.model.entity;

import com.spring.techpractica.model.SessionRequestUserId;
import com.spring.techpractica.model.UserSessionId;
import jakarta.persistence.*;

@Entity
@Table(name = "SESSION_REQUEST")
public class SessionOfRequest {
    @EmbeddedId
    private SessionRequestUserId sessionRequestUserId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @MapsId("requestId")
    @JoinColumn(name = "request_id")
    private Request request;
}
