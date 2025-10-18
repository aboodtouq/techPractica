package com.spring.techpractica.Core.Task.Entity;

import com.spring.techpractica.Core.Field.Entity.Field;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Shared.BaseEntity;
import com.spring.techpractica.Core.Task.Model.TaskType;
import com.spring.techpractica.Core.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "TASKS")
public class Task extends BaseEntity {

    @Column(name = "task_tittle")
    private String tittle;

    @Column(name = "task_description")
    private String description;

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

    private LocalDateTime dueDate;

    @Column(name = "Task_Type")
    private TaskType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "task_id"),
                inverseJoinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id"))
    private List<Field> fields;
}