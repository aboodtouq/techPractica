package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
