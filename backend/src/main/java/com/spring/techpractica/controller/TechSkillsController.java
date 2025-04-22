package com.spring.techpractica.controller;

import com.spring.techpractica.model.entity.Category;
import com.spring.techpractica.model.entity.Field;
import com.spring.techpractica.model.entity.Technology;
import com.spring.techpractica.service.CategoryService;
import com.spring.techpractica.service.FieldService;
import com.spring.techpractica.service.TechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/TechSkills")
@Tag(name = "Tech Skills API", description = "API for retrieving tech skills like categories, technologies, and fields")
public class TechSkillsController {


    private final CategoryService categoryService;
    private final TechnologyService technologyService;
    private final FieldService fieldService;

    public TechSkillsController(CategoryService categoryService, TechnologyService technologyService, FieldService fieldService) {
        this.categoryService = categoryService;
        this.technologyService = technologyService;
        this.fieldService = fieldService;
    }


    @Operation(summary = "Get all categories", description = "Returns a list of all skill categories")
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @Operation(summary = "Get all technologies", description = "Returns a list of all technologies")
    @GetMapping("/technologies")
    public ResponseEntity<List<Technology>> getTechnologies() {
        return ResponseEntity.ok(technologyService.findAllTechnologies());
    }

    @Operation(summary = "Get all technologies", description = "Returns a list of all technologies")
    @GetMapping("/fields")
    public ResponseEntity<List<Field>> getFields() {
        return ResponseEntity.ok(fieldService.findAllFields());
    }
}
