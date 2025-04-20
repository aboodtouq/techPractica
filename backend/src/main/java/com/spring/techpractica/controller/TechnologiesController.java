package com.spring.techpractica.controller;

import com.spring.techpractica.model.entity.Category;
import com.spring.techpractica.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/Technologies")

public class TechnologiesController {


    private final CategoryService categoryService;

    public TechnologiesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }
}
