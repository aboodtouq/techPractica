package com.spring.techpractica.application.session.task.update.state;

import com.spring.techpractica.core.task.model.TaskStatus;

import java.util.UUID;

public record UpdateTaskStateCommand(UUID taskId, TaskStatus status) {
}