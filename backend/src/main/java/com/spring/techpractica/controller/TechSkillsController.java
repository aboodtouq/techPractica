package com.spring.techpractica.controller;

import com.spring.techpractica.model.entity.Category;
import com.spring.techpractica.model.entity.Field;
import com.spring.techpractica.model.entity.Technology;
import com.spring.techpractica.service.CategoryService;
import com.spring.techpractica.service.FieldService;
import com.spring.techpractica.service.TechnologyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/TechSkills")

public class TechSkillsController {


    private final CategoryService categoryService;
    private final TechnologyService technologyService;
    private final FieldService fieldService;

    public TechSkillsController(CategoryService categoryService, TechnologyService technologyService, FieldService fieldService) {
        this.categoryService = categoryService;
        this.technologyService = technologyService;
        this.fieldService = fieldService;
    }


    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/technologies")
    public ResponseEntity<List<Technology>> getTechnologies() {
        return ResponseEntity.ok(technologyService.findAllTechnologies());
    }

    @GetMapping("/fields")
    public ResponseEntity<List<Field>> getFields() {
        return ResponseEntity.ok(fieldService.findAllFields());
    }
}
