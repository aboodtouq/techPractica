package com.spring.techpractica.UI.Rest.Controller.User.Manage;

import com.spring.techpractica.Application.User.Manage.GetUserBySpecificationsCommand;
import com.spring.techpractica.Application.User.Manage.GetUserBySpecificationsUseCase;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.UI.Rest.Resources.User.UserCollection;
import com.spring.techpractica.UI.Rest.Shared.Exception.InvalidPageRequestException;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class GetUserBySpecificationsController {

    private final GetUserBySpecificationsUseCase  getUserBySpecificationsUseCase;

    @GetMapping("/")
    public ResponseEntity<?>  getUserBySpecifications(@RequestParam(required = false) String userName,
                                                      @RequestParam(required = false) String role,
                                                      @RequestParam int page,
                                                      @RequestParam int size){

        if (page < 0 || size < 1) {
            throw new InvalidPageRequestException(page, size);
        }

        List<User> users = getUserBySpecificationsUseCase
                .execute(new GetUserBySpecificationsCommand(userName,role,page,size));

        UserCollection responseData = new UserCollection(users);

        return ResponseEntity.ok(StandardSuccessResponse.<UserCollection>builder()
                .data(responseData)
                .message("Get User By Specifications Successfully executed")
                .status(HttpStatus.OK.value())
                .build());

    }
}
