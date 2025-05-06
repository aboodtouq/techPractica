package com.spring.techpractica.model.entity.techSkills;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TECHNOLOGIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technology {

    @Id
    @Column(name = "technology_name")
    private String technologyName;

}
