package com.spring.techpractica.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "SESSIONS")
public class Sessions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int sessionId;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "session_description")
    private String sessionDescription;

    @Column(name = "is_private")
    private boolean isPrivate;

    @Column(name = "required_users")
    private int requiredUsers;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToMany(mappedBy = "sessions")
    private List<User> users;
}
