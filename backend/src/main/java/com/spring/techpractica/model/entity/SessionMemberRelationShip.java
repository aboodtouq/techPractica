package com.spring.techpractica.model.entity;

import com.spring.techpractica.model.UserSessionId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AUTHENTICATED_USER_SESSION")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionMemberRelationShip {

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

    @Column(name = "socoped_role")
    private String scopedRole;
}
