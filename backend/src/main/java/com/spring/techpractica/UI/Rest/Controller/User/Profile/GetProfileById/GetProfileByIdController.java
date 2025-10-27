package com.spring.techpractica.UI.Rest.Controller.User.Profile.GetProfileById;

import com.spring.techpractica.application.session.get.user.sessions.count.GetUserSessionsCountCommand;
import com.spring.techpractica.application.session.get.user.sessions.count.GetUserSessionsCountUseCase;
import com.spring.techpractica.application.session.get.user.sessions.GetUserSessionCommand;
import com.spring.techpractica.application.session.get.user.sessions.GetUserSessionsUseCase;
import com.spring.techpractica.application.user.profile.get.GetProfileCommand;
import com.spring.techpractica.application.user.profile.get.GetProfileUseCase;
import com.spring.techpractica.core.session.entity.Session;
import com.spring.techpractica.core.user.User;
import com.spring.techpractica.UI.Rest.Resources.Session.SessionCollection;
import com.spring.techpractica.UI.Rest.Resources.User.ProfileResources;
import com.spring.techpractica.UI.Rest.Resources.User.UserResources;
import com.spring.techpractica.UI.Rest.Shared.StandardErrorResponse;
import com.spring.techpractica.UI.Rest.Shared.StandardSuccessResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/profile")
@Tag(name = "Profile", description = "Profile related endpoints")
public class GetProfileByIdController {

    private final GetProfileUseCase getProfileUseCase;

    private final GetUserSessionsUseCase getUserSessionsUseCase;

    private final GetUserSessionsCountUseCase getUserSessionsCountUseCase;


    @Operation(
            summary = "Get user profile by id",
            description = "Allows an user to Get any profile information such as first name, last name, brief, skills, and social accounts and sessions by userId.",
            tags = {"Profile"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile returned successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardSuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed (invalid data provided)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    @GetMapping("/{userId}/")
    public ResponseEntity<?> getProfile(@PathVariable UUID userId) {

        User user = getProfileUseCase.execute(new GetProfileCommand(userId));

        List<Session> sessions =getUserSessionsUseCase.execute(new GetUserSessionCommand(user.getId(),6,0)).getContent();

        ProfileResources response = new ProfileResources(new UserResources(user)
                ,new SessionCollection(sessions,getUserSessionsCountUseCase.execute(new GetUserSessionsCountCommand(user.getId()))));

        return ResponseEntity.accepted().body(StandardSuccessResponse.builder()
                .data(response)
                .message("Profile returned successfully")
                .status(HttpStatus.ACCEPTED.value())
                .build());
    }

}