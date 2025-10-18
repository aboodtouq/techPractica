package com.spring.techpractica.infrastructure.Jpa.Task;

import com.spring.techpractica.Core.Task.Entity.Task;
import com.spring.techpractica.Core.Task.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class JpaTaskRepository implements TaskRepository {

    private final TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return Optional.empty();
    }
}