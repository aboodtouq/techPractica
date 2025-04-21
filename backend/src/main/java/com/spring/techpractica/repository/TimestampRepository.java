package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimestampRepository extends JpaRepository<Timestamp, Long> {
}
