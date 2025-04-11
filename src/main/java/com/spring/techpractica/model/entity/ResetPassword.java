package com.spring.techpractica.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESET_PASSWORD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reset_password_id")
    private Long resetPasswordId;

    @Column(name = "user_email")
    private String userEmail;

    private String code;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "is_used")
    private boolean isUsed;

}
