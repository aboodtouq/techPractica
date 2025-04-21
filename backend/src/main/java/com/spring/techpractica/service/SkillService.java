package com.spring.techpractica.service;

import com.spring.techpractica.model.entity.Technology;
import com.spring.techpractica.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Technology> findAll() {
        return skillRepository.findAll();
    }

}
