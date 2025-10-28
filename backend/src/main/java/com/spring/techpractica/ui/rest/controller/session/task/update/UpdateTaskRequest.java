package com.spring.techpractica.ui.rest.controller.session.task.update;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UpdateTaskRequest(UUID sessionId, UUID taskId,String title, String description,
                                String type, LocalDateTime dueDate,
                                Set<UUID> assignees, Set<String> tags) { }