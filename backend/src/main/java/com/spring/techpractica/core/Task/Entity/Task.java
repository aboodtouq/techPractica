package com.spring.techpractica.core.Task.Entity;

import com.spring.techpractica.core.Session.Entity.Session;
import com.spring.techpractica.core.Shared.BaseEntity;
import com.spring.techpractica.core.User.User;
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
@Table(name = "TASKS")
public class Task extends BaseEntity {

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "user_owner_id", referencedColumnName = "id")
    private User userOwnerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "task_id")
            , inverseJoinColumns = @JoinColumn(name = "user_assign_id", referencedColumnName = "id")
    )
    private List<User> usersAssigned;
}
