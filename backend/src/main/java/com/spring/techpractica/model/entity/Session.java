package com.spring.techpractica.model.entity;

import com.spring.techpractica.model.TimestampType;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.model.entity.techSkills.Technology;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "SESSIONS")
@NoArgsConstructor
public class Session {


    @PrePersist
    public void prePersist() {
        sessionIsRunning = false;

        timestampList.add(
                Timestamp.builder()
                        .eventDate(LocalDate.now())
                        .eventType(TimestampType.CREATED)
                        .build());

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private long sessionId;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "session_description")
    private String sessionDescription;

    @Column(name = "is_private")
    private boolean isPrivate;


    @OneToMany(mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<AuthenticatedUserSession> sessionMembers = new ArrayList<>();

    @Column(name = "is_running")
    private boolean sessionIsRunning;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "timestamp_id")
    )
    private List<Timestamp> timestampList = new ArrayList<>();


    @OneToMany(mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE})
    private List<Requirement> sessionRequirements = new ArrayList<>();

    @OneToMany(mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<Task> tasks = new ArrayList<>();


    @OneToMany(mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<Request> sessionRequests = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "CATEGORIES_SESSIONS"
            , joinColumns = @JoinColumn(name = "category_name")
            , inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private List<Category> sessionCategories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "TECHNOLOGIES_SESSIONS"
            , joinColumns = @JoinColumn(name = "technology_name")
            , inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private List<Technology> sessionTechnologies = new ArrayList<>();
}
