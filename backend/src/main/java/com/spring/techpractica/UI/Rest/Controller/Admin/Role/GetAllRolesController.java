package com.spring.techpractica.UI.Rest.Controller.Admin.Role;

import com.spring.techpractica.Application.Admin.Role.GetAllRolesUseCase;
import com.spring.techpractica.Core.Role.Entity.Role;
import com.spring.techpractica.UI.Rest.Resources.Role.RoleCollection;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@AllArgsConstructor
public class GetAllRolesController {

    private final GetAllRolesUseCase getAllRolesUseCase;

    @GetMapping("/")
    public ResponseEntity<?> getAllRoles(){
        List<Role> roles = getAllRolesUseCase.getAllRoles();

        RoleCollection roleCollection = new RoleCollection(roles);

        return ResponseEntity.ok(StandardSuccessResponse.<RoleCollection>builder()
                .data(roleCollection)
                .message("Get all roles done successfully")
                .status(HttpStatus.OK.value())
                .build());
    }
}
