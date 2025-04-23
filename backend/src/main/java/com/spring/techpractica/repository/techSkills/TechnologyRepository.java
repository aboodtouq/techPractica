package com.spring.techpractica.repository.techSkills;

import com.spring.techpractica.model.entity.techSkills.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, String> {
}
