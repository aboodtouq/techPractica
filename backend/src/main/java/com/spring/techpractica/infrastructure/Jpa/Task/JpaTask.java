package com.spring.techpractica.infrastructure.Jpa.Task;

import com.spring.techpractica.Core.Task.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTask extends JpaRepository<Task, UUID> {
}
