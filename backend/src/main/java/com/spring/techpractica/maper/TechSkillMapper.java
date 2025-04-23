package com.spring.techpractica.maper;

import com.spring.techpractica.dto.techSkills.CategoryResponse;
import com.spring.techpractica.dto.techSkills.FieldTransfer;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.model.entity.techSkills.Field;
import com.spring.techpractica.model.entity.techSkills.Technology;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TechSkillMapper {

    public CategoryResponse CategoryToCategoryResponse(Category category) {
        if (category.getTechnologies().isEmpty()) {
            category.setTechnologies(new ArrayList<>());
        }
        return CategoryResponse.builder().categoryName(category.getCategoryName())
                .technologies(technologiesToListString(category.getTechnologies()))
                .build();

    }

    public List<CategoryResponse> categoryToCategoryResponseList(List<Category> categories) {
        return categories.stream().map(this::CategoryToCategoryResponse).toList();

    }

    private List<String> technologiesToListString(List<Technology> technologies) {
        return technologies.stream().map((Technology::getTechnologyName))
                .toList();
    }


    public FieldTransfer fieldToFieldTransfer(Field field) {

       return FieldTransfer.builder().fieldName(field.getFieldName())
               .build();

    }

    public List<FieldTransfer> fieldToFieldTransferList(List<Field> fields) {
        return fields.stream().map(this::fieldToFieldTransfer).toList();

    }
}
