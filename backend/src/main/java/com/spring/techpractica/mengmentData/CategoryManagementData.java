package com.spring.techpractica.mengmentData;

import com.spring.techpractica.dto.techSkills.CategoryResponse;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.maper.CategoryMapper;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.repository.techSkills.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManagementData {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryManagementData(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.categoryToCategoryResponseList(categoryRepository.findAll());
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findById(name)
                .orElseThrow(()
                        -> new ResourcesNotFoundException("categorise no concentrate"));
    }
}
