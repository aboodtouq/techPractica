package com.spring.techpractica.model.entity;


import com.spring.techpractica.model.SkillType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SKILLS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @Enumerated(EnumType.STRING)
    private SkillType skillName;

}
