package com.spring.techpractica.model.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Task")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;


    @Column(name = "session_id")
    private int sessionId;

    @Column(name = "user_owner_id")
    private String userOwnerId;


}
