package com.spring.techpractica.ui.rest.Controller.Admin.Field.CreateField;

import jakarta.validation.constraints.NotBlank;

public record CreateFieldRequest(@NotBlank String name) {
}
