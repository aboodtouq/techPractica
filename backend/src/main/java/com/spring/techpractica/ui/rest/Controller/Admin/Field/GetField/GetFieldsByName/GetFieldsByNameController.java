package com.spring.techpractica.ui.rest.Controller.Admin.Field.GetField.GetFieldsByName;


import com.spring.techpractica.application.admin.field.get.by.name.GetFieldsByNameCommand;
import com.spring.techpractica.application.admin.field.get.by.name.GetFieldsByNameUseCase;
import com.spring.techpractica.core.field.entity.Field;
import com.spring.techpractica.ui.rest.Resources.Field.FieldCollection;
import com.spring.techpractica.ui.rest.Shared.StandardSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/fields")
@AllArgsConstructor
@Tag(name = "Admin - Field")
@Validated
public class GetFieldsByNameController {
    private final GetFieldsByNameUseCase getFieldsByNameUseCase;

    @Operation(summary = "Get fields by names", description = "Return fields that match the provided names (use query parameter `names`).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fields returned",
                    content = @Content(schema = @Schema(implementation = FieldCollection.class))),
            @ApiResponse(responseCode = "404", description = "One or more Fields not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request (e.g. empty names)", content = @Content)
    })
    @GetMapping("/name")
    public ResponseEntity<?> getFieldsByName(
            @RequestParam(name = "names") @NotEmpty @Size(min = 1) List<@NotBlank String> names) {

            List<Field> fields = getFieldsByNameUseCase.execute(new GetFieldsByNameCommand(names));
            FieldCollection responseDataList = new FieldCollection(fields);

                return ResponseEntity.status(HttpStatus.CREATED).body(
                        StandardSuccessResponse.builder()
                                .data(responseDataList)
                                .message("Fields returned successfully")
                                .status(HttpStatus.OK.value())
                                .build()
                );


    }
}
