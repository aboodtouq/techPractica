package com.spring.techpractica.application.session.task.update;

import com.spring.techpractica.core.field.FieldRepository;
import com.spring.techpractica.core.field.entity.Field;
import com.spring.techpractica.core.session.SessionRepository;
import com.spring.techpractica.core.session.entity.Session;
import com.spring.techpractica.core.shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.shared.Exception.UnauthorizedActionException;
import com.spring.techpractica.core.task.TaskRepository;
import com.spring.techpractica.core.task.entity.Task;
import com.spring.techpractica.core.user.User;
import com.spring.techpractica.core.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTaskUseCase {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final FieldRepository fieldRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public Task execute(UpdateTaskCommand command) {
        Session session = sessionRepository.getOrThrowByID(command.sessionId());
        validateSessionOwnership(session, command.ownerId());

        List<Field> fields = validateAndGetFields(command.tags());
        List<User> assignees = validateAndGetAssignees(command.assignees());
        Task task = taskRepository.getOrThrowByID(command.taskId());

        task.setDescription(command.description());
        task.setTittle(command.title());
        task.setUsersAssigned(assignees);
        task.setType(command.type());
        task.setDueDate(command.dueDate());
        task.setFields(fields);


        return taskRepository.save(task);
    }

    private void validateSessionOwnership(Session session, UUID ownerId) {
        if (!session.isOwner(ownerId)) {
            throw new UnauthorizedActionException("User must be the session owner to update tasks.");
        }
    }

    private List<Field> validateAndGetFields(Set<String> tagNames) {
        List<Field> fields = fieldRepository.findAllByNames(tagNames);
        if (fields.size() != tagNames.size()) {
            throw new ResourcesNotFoundException(tagNames.stream().toList());
        }
        return fields;
    }

    private List<User> validateAndGetAssignees(Set<UUID> assigneeIds) {
        List<User> users = userRepository.findAllByIds(assigneeIds);
        if (users.size() != assigneeIds.size()) {
            throw new ResourcesNotFoundException("Some assignees were not found.");
        }
        return users;
    }
}
