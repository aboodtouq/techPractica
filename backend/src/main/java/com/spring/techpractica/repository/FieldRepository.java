package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
