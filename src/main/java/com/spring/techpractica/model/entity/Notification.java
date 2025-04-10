package com.spring.techpractica.model.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Notification_id")
    private long notificationsId;

    @Column(name = "notfication_content")
    private String NotificationsContent;

    @Column(name = "user_id")
    private int userId;

}
