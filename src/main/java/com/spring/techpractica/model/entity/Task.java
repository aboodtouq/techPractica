package com.spring.techpractica.model.entity;

import jakarta.persistence.*;
import lombok.*;

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


    @Column(name = "session_id")
    private int sessionId;

    @Column(name = "user_owner_id")
    private String userOwnerId;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "session_id",insertable=false, updatable=false)
    private Session session;

}
