package com.spring.techpractica.UI.Rest.Controller.User.Manage;

import com.spring.techpractica.Application.User.Manage.GetUserBySpecificationsCommand;
import com.spring.techpractica.Application.User.Manage.GetUserBySpecificationsUseCase;
import com.spring.techpractica.Core.User.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class GetUserBySpecificationsController {

    private final GetUserBySpecificationsUseCase  getUserBySpecificationsUseCase;

    public ResponseEntity<?>  getUserBySpecifications(@RequestParam(required = false) String userName,
                                                      @RequestParam(required = false) String role,
                                                      @RequestParam int page,
                                                      @RequestParam int size){
        List<User> users = getUserBySpecificationsUseCase
                .execute(new GetUserBySpecificationsCommand(userName,role,page,size));



    }
}
