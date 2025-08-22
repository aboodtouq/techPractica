package com.spring.techpractica.Core.Session.Entity;

import com.spring.techpractica.Core.SessionMembers.Entity.SessionMember;
import com.spring.techpractica.Core.Field.Entity.Field;
import com.spring.techpractica.Core.Request.Entity.Request;
import com.spring.techpractica.Core.Requirement.Entity.Requirement;
import com.spring.techpractica.Core.Shared.BaseEntity;
import com.spring.techpractica.Core.System.Entity.System;
import com.spring.techpractica.Core.Task.Entity.Task;
import com.spring.techpractica.Core.Technology.Entity.Technology;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "SESSIONS")
@NoArgsConstructor
public class Session extends BaseEntity {
    @Column(name = "session_name")
    private String name;

    @Column(name = "session_description",
            length = 1000)
    private String description;

    @Column(name = "is_private")
    private boolean isPrivate;

    @OneToMany(mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<SessionMember> members = new ArrayList<>();

    @Column(name = "is_running")
    private boolean isRunning;


    @OneToMany(mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE,
                    CascadeType.PERSIST},
            orphanRemoval = true)
    private List<Requirement> requirements = new ArrayList<>();

    @OneToMany(mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.REMOVE, CascadeType.MERGE})
    private List<Request> requests = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SYSTEMS_SESSIONS",
            joinColumns = @JoinColumn(name = "session_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_id", referencedColumnName = "id")
    )
    private List<System> systems = new ArrayList<>();

    public void addMember(SessionMember sessionMember) {
        if (members == null) {
            members = new ArrayList<>();
        }
        members.add(sessionMember);
    }

    public void addSystem(System system) {
        if (systems == null) {
            systems = new ArrayList<>();
        }
        systems.add(system);
    }

    public void addRequirement(Requirement requirement) {
        if (requirements == null) {
            requirements = new ArrayList<>();
        }
        requirements.add(requirement);
    }
}
