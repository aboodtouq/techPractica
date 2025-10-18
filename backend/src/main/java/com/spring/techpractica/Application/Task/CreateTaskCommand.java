package com.spring.techpractica.Application.Task;

import com.spring.techpractica.Core.Task.Model.TaskType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CreateTaskCommand (UUID ownerId, UUID sessionId, String title,
                                 String description, TaskType type, LocalDateTime dueDate,
                                 Set<UUID> assignees, Set<String> tags){
}