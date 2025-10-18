package com.spring.techpractica.UI.Rest.Controller.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record CreateTaskRequest(UUID sessionId, String title, String description,
                                String type, LocalDateTime dueDate,
                                Set<UUID> assignees, Set<String> tags) {
}