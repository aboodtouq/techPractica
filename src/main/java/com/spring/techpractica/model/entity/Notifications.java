package com.spring.techpractica.model.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Notifications")
@Getter
@Setter
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Notification_id")
    private int notificationsId;

    @Column(name = "notfication_content")
    private String NotificationsContent;

    @Column(name = "user_id")
    private int userId;

}
