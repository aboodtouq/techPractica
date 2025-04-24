package com.spring.techpractica.service.session.createSession;

import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.service.techSkills.Category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SessionCategoryLinker {

    private final CategoryService categoryService;

    public void linkCategoryToSession(Session session, String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        session.getSessionCategories().add(category);
    }
}
