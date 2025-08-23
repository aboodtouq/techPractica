package com.spring.techpractica.Core.User;

import com.spring.techpractica.Core.Notification.Entity.Notification;
import com.spring.techpractica.Core.Request.Entity.Request;
import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.Core.Shared.BaseEntity;
import com.spring.techpractica.Core.SocialAccount.Entity.SocialAccount;
import com.spring.techpractica.Core.Task.Entity.Task;
import com.spring.techpractica.Core.Technology.Entity.Technology;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "USERS")
public class User extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.UNACTIVE_ACCOUNT;

    public boolean isProfileComplete() {
        return accountStatus.equals(AccountStatus.COMPLETE_PROFILE);
    }

    public void activate() {
        accountStatus = AccountStatus.ACTIVE_ACCOUNT;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_SKILLS",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id", referencedColumnName = "id"))
    private List<Technology> userTechnologies;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<SocialAccount> socialAccounts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Request> requests;

    @ManyToMany(mappedBy = "usersAssigned", fetch = FetchType.LAZY)
    private List<Task> tasksAssigned;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Notification> notifications;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
}
