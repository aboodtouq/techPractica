package com.spring.techpractica.model.entity;


import com.spring.techpractica.model.SkillType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "technology_name"),
            inverseJoinColumns = @JoinColumn(name = "category_name")
    )
    private List<Category> categories;
}
