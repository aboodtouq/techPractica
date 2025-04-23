package com.spring.techpractica.repository.techSkills;


import com.spring.techpractica.model.entity.techSkills.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> getCategoriesByCategoryName(String categoryName);
}
