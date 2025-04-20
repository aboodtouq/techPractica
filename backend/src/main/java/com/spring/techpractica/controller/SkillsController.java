package com.spring.techpractica.controller;

import com.spring.techpractica.repository.SkillRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/skill")

public class SkillsController {

    private final SkillRepository skillRepository;

    public SkillsController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

}
