package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.Category;
import com.spring.techpractica.model.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {


//    @Query("select s from sessions s where s.category_name = :categoryName")
//    Page<Session> findAllBySessionCategoryName( String categoryName, Pageable sessionPage);

    Page<Session> findAllBySessionCategories(Category category, Pageable pageable);
}
