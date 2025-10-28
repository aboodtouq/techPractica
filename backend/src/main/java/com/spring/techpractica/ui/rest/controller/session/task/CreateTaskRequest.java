package com.spring.techpractica.ui.rest.controller.session.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record CreateTaskRequest(UUID sessionId, String title, String description,
                                String type, LocalDateTime dueDate,
                                Set<UUID> assignees, Set<String> tags) { }