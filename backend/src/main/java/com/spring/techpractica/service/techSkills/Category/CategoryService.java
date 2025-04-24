package com.spring.techpractica.service.techSkills.Category;

import com.spring.techpractica.dto.techSkills.CategoryResponse;
import com.spring.techpractica.mengmentData.CategoryManagementData;
import com.spring.techpractica.model.entity.techSkills.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    private final CategoryManagementData categoryManagementData;

    public CategoryService(CategoryManagementData categoryManagementData) {
        this.categoryManagementData = categoryManagementData;
    }


    public List<CategoryResponse> findAllCategories() {
        return categoryManagementData.getAllCategories();
    }

    public Category getCategoryByName(String name) {
        return categoryManagementData.getCategoryByName(name);
    }

}
