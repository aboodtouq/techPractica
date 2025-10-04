package com.spring.techpractica.UI.Rest.Controller.User.Manage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class GetUserBySpecificationsController {

    public ResponseEntity<?>  getUserBySpecifications(@RequestParam(required = false) String userName,
                                                      @RequestParam(required = false) String role){

    }
}
