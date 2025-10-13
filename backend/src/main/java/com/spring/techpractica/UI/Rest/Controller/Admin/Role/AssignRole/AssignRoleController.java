package com.spring.techpractica.UI.Rest.Controller.Admin.Role.AssignRole;

import com.spring.techpractica.Application.Admin.Role.AssignRole.AssignRoleCommand;
import com.spring.techpractica.Application.Admin.Role.AssignRole.AssignRoleUseCase;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@AllArgsConstructor
public class AssignRoleController {

    private final AssignRoleUseCase  assignRoleUseCase;

    @PutMapping("/assign")
    public ResponseEntity<?> assignRole(@RequestBody AssignRoleRequest request){
        String result = assignRoleUseCase.execute(new AssignRoleCommand(request.id(), request.roles()));

        return ResponseEntity.ok(StandardSuccessResponse.<String>builder()
                .data(result)
                .message("Role Assigned Successfully")
                .status(HttpStatus.OK.value())
                .build()
        );
    }
}
