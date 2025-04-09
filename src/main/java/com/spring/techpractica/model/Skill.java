package com.spring.techpractica.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SKILLS")
@Data
public class Skill {

    @Id
    @Enumerated(EnumType.STRING)
    private SkillType skillName;

}
