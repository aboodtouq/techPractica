package com.spring.techpractica.core.Field.Entity;

import com.spring.techpractica.core.Requirement.Entity.Requirement;
import com.spring.techpractica.core.Shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "FIELDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "field")
    private List<Requirement> requirements;
}