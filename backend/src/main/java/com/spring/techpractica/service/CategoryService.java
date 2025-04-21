package com.spring.techpractica.service;

import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.model.entity.Category;
import com.spring.techpractica.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryByName(String name) {
        return  categoryRepository.findById(name).orElseThrow(()->new ResourcesNotFoundException("categoria no encontrada"));

    }
}
