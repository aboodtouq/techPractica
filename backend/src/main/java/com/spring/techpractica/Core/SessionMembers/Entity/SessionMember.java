package com.spring.techpractica.Core.SessionMembers.Entity;

import com.spring.techpractica.Core.SessionMembers.model.Role;
import com.spring.techpractica.Core.SessionMembers.model.UserSessionId;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SESSION_MEMBERS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionMember {

    @EmbeddedId
    private UserSessionId userSessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sessionId")
    @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
    private Session session;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public void setUserAndSession(User user, Session session) {
        this.user = user;
        this.session = session;
        this.userSessionId = new UserSessionId(user.getId(), session.getId());
    }
}
