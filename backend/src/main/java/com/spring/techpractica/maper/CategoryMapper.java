package com.spring.techpractica.maper;

import com.spring.techpractica.dto.techSkills.CategoryResponse;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.model.entity.techSkills.Technology;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {


    private CategoryMapper() {
    }

    public static CategoryResponse CategoryToCategoryResponse(Category category) {
        if (category.getTechnologies().isEmpty()) {
            category.setTechnologies(new ArrayList<>());
        }
        return CategoryResponse.builder().categoryName(category.getCategoryName())
                .technologies(TechnologyMapper.technologiesToListString(category.getTechnologies()))
                .build();

    }

    public static List<CategoryResponse> categoryToCategoryResponseList(List<Category> categories) {
        return categories.stream().map(CategoryMapper::CategoryToCategoryResponse).toList();

    }


}
