package com.spring.techpractica.UI.Rest.Controller.Admin.Manage;

import com.spring.techpractica.Application.Admin.Manage.DeleteUser.DeleteUserCommand;
import com.spring.techpractica.Application.Admin.Manage.DeleteUser.DeleteUserUseCase;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class DeleteUserController {

    private final DeleteUserUseCase  deleteUserUseCase;

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        String result = deleteUserUseCase.execute(new DeleteUserCommand(userId));

        return ResponseEntity.ok(StandardSuccessResponse.<String>builder()
                .data(result)
                .message("Delete User Successfully")
                .status(HttpStatus.OK.value())
                .build());
    }
}