package com.spring.techpractica.ui.rest.Controller.Admin.System.CreateSystem;

import jakarta.validation.constraints.NotBlank;

public record CreateSystemRequest(@NotBlank String name) {
}
