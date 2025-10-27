package com.spring.techpractica.ui.rest.Controller.Admin.Manage;

import com.spring.techpractica.application.admin.manage.get.user.by.specification.GetUserBySpecificationsCommand;
import com.spring.techpractica.application.admin.manage.get.user.by.specification.GetUserBySpecificationsUseCase;
import com.spring.techpractica.core.user.User;
import com.spring.techpractica.ui.rest.Resources.User.UserCollection;
import com.spring.techpractica.ui.rest.Shared.Exception.InvalidPageRequestException;
import com.spring.techpractica.ui.rest.Shared.StandardSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Management", description = "Endpoints for managing and filtering users")
public class GetUserBySpecificationsController {

    private final GetUserBySpecificationsUseCase getUserBySpecificationsUseCase;

    @Operation(
            summary = "Get users by specifications",
            description = "Fetch users filtered by optional username and role, with pagination support."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully",
                    content = @Content(schema = @Schema(implementation = StandardSuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid page or size parameters",
                    content = @Content(schema = @Schema(implementation = InvalidPageRequestException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    public ResponseEntity<?> getUserBySpecifications(@RequestParam(required = false) String userName,
                                                     @RequestParam(required = false) String role,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size < 1) {
            throw new InvalidPageRequestException(page, size);
        }

        List<User> users = getUserBySpecificationsUseCase
                .execute(new GetUserBySpecificationsCommand(userName, role, page, size));

        UserCollection responseData = new UserCollection(users);

        return ResponseEntity.ok(StandardSuccessResponse.<UserCollection>builder()
                .data(responseData)
                .message("Get User By Specifications Successfully executed")
                .status(HttpStatus.OK.value())
                .build());
    }
}