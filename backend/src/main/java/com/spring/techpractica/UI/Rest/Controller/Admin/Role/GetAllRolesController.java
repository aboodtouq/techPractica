package com.spring.techpractica.UI.Rest.Controller.Admin.Role;

import com.spring.techpractica.Application.Admin.Role.GetAllRolesUseCase;
import com.spring.techpractica.Core.Role.Entity.Role;
import lombok.AllArgsConstructor;
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

        return ResponseEntity.ok(roles);
    }
}
