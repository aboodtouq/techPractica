package com.spring.techpractica.model.entity.techSkills;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FIELDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {

    @Id
    @Column(name = "field_name")
    private String fieldName;
}
