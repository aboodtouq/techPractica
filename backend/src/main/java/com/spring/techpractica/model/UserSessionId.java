package com.spring.techpractica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Builder
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionId implements Serializable {
    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "user_id")
    private Long userId;

}

