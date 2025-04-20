package com.spring.techpractica.model.entity;


import com.spring.techpractica.model.SkillType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "SKILLS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_name")
    private SkillType skillName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "skill_name"),
            inverseJoinColumns = @JoinColumn(name = "category_name")
    )
    private List<Category> categories;
}
