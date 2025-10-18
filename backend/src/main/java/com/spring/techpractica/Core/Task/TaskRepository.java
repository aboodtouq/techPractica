package com.spring.techpractica.Core.Task;

import com.spring.techpractica.Core.Shared.BaseRepository;
import com.spring.techpractica.Core.Task.Entity.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<Task> {
}