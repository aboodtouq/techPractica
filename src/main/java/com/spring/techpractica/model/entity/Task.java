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
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "user_owner_id")
    private String userOwnerId;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "timestamp_id"))
    private List<Timestamp> timestampList;

}
