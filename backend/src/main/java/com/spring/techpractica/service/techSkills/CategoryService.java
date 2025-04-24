package com.spring.techpractica.service.techSkills;

import com.spring.techpractica.dto.techSkills.CategoryResponse;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.maper.TechSkillMapper;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.repository.techSkills.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final TechSkillMapper techSkillMapper;

    public CategoryService(CategoryRepository categoryRepository,
                           TechSkillMapper techSkillMapper) {
        this.categoryRepository = categoryRepository;
        this.techSkillMapper = techSkillMapper;
    }

    public List<CategoryResponse> getAllCategories() {
        return techSkillMapper.categoryToCategoryResponseList(categoryRepository.findAll());
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findById(name)
                .orElseThrow(() -> new ResourcesNotFoundException("categorise no concentrate"));


    }
}
