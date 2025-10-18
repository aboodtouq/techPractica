package com.spring.techpractica.Application.Task;

import com.spring.techpractica.Core.Field.Entity.Field;
import com.spring.techpractica.Core.Field.FieldRepository;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.Shared.Exception.UnauthorizedActionException;
import com.spring.techpractica.Core.Task.Entity.Task;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateTaskUseCase {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final FieldRepository fieldRepository;

    @Transactional
    public Task execute(CreateTaskCommand command) {
        Session session = sessionRepository.getOrThrowByID(command.sessionId());

        boolean isOwner = session.isOwner(command.ownerId());

        if (!isOwner) {
            throw new UnauthorizedActionException("User must be the session owner to assign task");
        }

        Set<String> tags = command.tags();

        List<Field> fields = fieldRepository.findAllByNames(tags);

        if (fields.size() != tags.size()) {
            throw new ResourcesNotFoundException(tags.stream().toList());
        }

        User owner = userRepository.getOrThrowByID(command.ownerId());

        Set<UUID> assigneesUsersIds = command.assignees();

        List<User> users = userRepository.findAllByIds(assigneesUsersIds);

        if (users.size() != assigneesUsersIds.size()) {
            throw new ResourcesNotFoundException("assigneesUsersIds not found");
        }

        Task task = Task.builder()
                .fields(fields)
                .type(command.type())
                .tittle(command.title())
                .description(command.description())
                .dueDate(command.dueDate())
                .userOwnerId(owner)
                .usersAssigned(users)
                .build();

    }
}