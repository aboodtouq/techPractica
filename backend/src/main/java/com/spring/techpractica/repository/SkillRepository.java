package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.techSkills.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Technology, String> {

}
