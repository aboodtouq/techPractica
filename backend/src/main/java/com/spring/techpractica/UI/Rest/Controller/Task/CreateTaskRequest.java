package com.spring.techpractica.UI.Rest.Controller.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateTaskRequest(String title, String description,
                                String type, LocalDateTime dueDate,
                                List<UUID> assignees, List<String> tags) {
}