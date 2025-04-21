package com.spring.techpractica.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FIELDS")
public class Field {

    @Id
    private String fieldName;
}
