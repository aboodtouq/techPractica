package com.spring.techpractica.model.entity;


import com.spring.techpractica.model.TimestampType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TIMESTAMP")
public class Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timestamp_id")
    private Long timestampId;


    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private TimestampType eventType;

    @Column(name = "event_date")
    private LocalDate eventDate;

}
