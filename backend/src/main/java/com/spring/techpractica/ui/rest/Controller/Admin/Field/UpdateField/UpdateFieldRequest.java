package com.spring.techpractica.ui.rest.Controller.Admin.Field.UpdateField;

import jakarta.validation.constraints.NotBlank;

public record UpdateFieldRequest(@NotBlank String name) {
}
