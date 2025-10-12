package com.spring.techpractica.UI.Rest.Controller.Admin.Role.AssignRole;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@AllArgsConstructor
public class AssignRoleController {

    public ResponseEntity<?> assignRole(AssignRoleRequest request){

    }
}
