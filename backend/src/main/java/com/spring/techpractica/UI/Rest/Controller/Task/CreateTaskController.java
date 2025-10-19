package com.spring.techpractica.UI.Rest.Controller.Task;

import com.spring.techpractica.Application.Task.CreateTaskCommand;
import com.spring.techpractica.Application.Task.CreateTaskUseCase;
import com.spring.techpractica.Core.Task.Entity.Task;
import com.spring.techpractica.Core.Task.Model.TaskType;
import com.spring.techpractica.Core.User.UserAuthentication;
import com.spring.techpractica.UI.Rest.Resources.Task.TaskResources;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class CreateTaskController {

    private final CreateTaskUseCase createTaskUseCase;

    @PostMapping("/")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest request,
                                        @AuthenticationPrincipal UserAuthentication userAuthentication) {
        UUID ownerId = userAuthentication.getUserId();

        Task task = createTaskUseCase.execute(
                new CreateTaskCommand(
                        ownerId,
                        request.sessionId(),
                        request.title(),
                        request.description(),
                        TaskType.valueOf(request.type().toUpperCase()),
                        request.dueDate(),
                        request.assignees(),
                        request.tags()
                )
        );

        TaskResources data = new TaskResources(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                StandardSuccessResponse.<TaskResources>builder()
                        .data(data)
                        .message("Task created successfully")
                        .status(HttpStatus.CREATED.value())
                        .build()
        );
    }
}