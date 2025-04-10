package com.spring.techpractica.model.entity;

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
    private long userId;

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

    @OneToMany(mappedBy = "user")
    private List<UsersOfSession> usersOfSessions;

    @ManyToMany
    @JoinTable(name = "USERS_SKILLS",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_type"))
    private List<Skill> userSkills;



    @OneToMany(mappedBy = "user")
    private List<SocialAccount> userSocialAccounts;


    @OneToMany(mappedBy = "user")
    private List<Request> requests;


    @ManyToMany(mappedBy = "usersAssigned")
    private List<Task> tasksAssigned;

    @OneToMany(mappedBy = "user")
    private List <Notification> notifications;
}
