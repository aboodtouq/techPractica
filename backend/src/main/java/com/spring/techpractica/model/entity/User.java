package com.spring.techpractica.model.entity;

import com.spring.techpractica.model.entity.techSkills.Technology;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "first_name")
    private String userFirstName;

    @Column(name = "last_name")
    private String userLastName;

    @Column(name = "user_email")
    private String userEmail;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AuthenticatedUserSession> authenticatedUserSessions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_SKILLS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_type"))
    private List<Technology> userTechnologies;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<SocialAccount> userSocialAccounts;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Request> requests;


    @ManyToMany(mappedBy = "usersAssigned", fetch = FetchType.LAZY)
    private List<Task> tasksAssigned;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Notification> notifications;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "role_name"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Role> roles;


}
