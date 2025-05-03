package com.spring.techpractica.service.session.linker;

import com.spring.techpractica.mengmentData.CategoryManagementData;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.techSkills.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SessionCategoryLinker {

    private final CategoryManagementData categoryManagementData;

    public void linkCategoryToSession(Session session, String categoryName) {
        Category category = categoryManagementData.getCategoryByName(categoryName);
        session.getSessionCategories().add(category);
    }
}
