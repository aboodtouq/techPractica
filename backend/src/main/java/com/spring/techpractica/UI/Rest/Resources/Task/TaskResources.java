package com.spring.techpractica.UI.Rest.Resources.Task;

import com.spring.techpractica.Core.Field.Entity.Field;
import com.spring.techpractica.Core.Shared.BaseEntity;
import com.spring.techpractica.Core.Task.Entity.Task;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class TaskResources {

    private final UUID id;
    private final String title;
    private final String description;
    private final String type;
    private final LocalDateTime dueDate;
    private final List<UUID> assignees;
    private final List<String> tags;

    public TaskResources(Task task) {
        this.id = task.getId();
        this.title = task.getTittle();
        this.description = task.getDescription();
        this.type = task.getType().name();
        this.dueDate = task.getDueDate();

        this.assignees = task.getUsersAssigned()
                .stream()
                .map(BaseEntity::getId)
                .toList();

        this.tags = task.getFields()
                .stream()
                .map(Field::getName)
                .toList();
    }
}
