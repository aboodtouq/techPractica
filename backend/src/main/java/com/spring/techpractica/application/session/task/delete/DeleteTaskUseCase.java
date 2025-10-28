package com.spring.techpractica.application.session.task.delete;

import com.spring.techpractica.core.session.SessionRepository;
import com.spring.techpractica.core.session.SessionStatus;
import com.spring.techpractica.core.session.entity.Session;
import com.spring.techpractica.core.shared.Exception.UnauthorizedActionException;
import com.spring.techpractica.core.task.TaskRepository;
import com.spring.techpractica.core.task.entity.Task;
import com.spring.techpractica.core.task.model.TaskStatus;
import com.spring.techpractica.core.user.User;
import com.spring.techpractica.core.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class DeleteTaskUseCase {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public Task execute(DeleteTaskCommand command) {
        User user = userRepository.getOrThrowByID(command.userId());

        Session session = sessionRepository.getOrThrowByID(command.sessionId());

        validateSessionOwner(session, user);

        Task task = taskRepository.getOrThrowByID(command.taskId());

        task.setStatus(TaskStatus.DELETED);

        return taskRepository.save(task);
    }

    private void validateSessionOwner(Session session, User user) {
        boolean isOwner = session.isOwner(user.getId());

        if (!isOwner) {
            throw new UnauthorizedActionException("User must be the session owner to delete the task");
        }
    }

}