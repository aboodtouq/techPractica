package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.model.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {



    Page<Session> findAllBySessionCategories(Category category, Pageable pageable);
}
