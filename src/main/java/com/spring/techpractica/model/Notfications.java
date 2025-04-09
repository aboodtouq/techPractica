package com.spring.techpractica.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Notfications")
@Getter
@Setter
public class Notfications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notfication_id")
    private int notficationId;
    @Column(name = "notfication_content")
    private String notficationContent;
    @Column(name = "user_id")
    private int userId;

}
