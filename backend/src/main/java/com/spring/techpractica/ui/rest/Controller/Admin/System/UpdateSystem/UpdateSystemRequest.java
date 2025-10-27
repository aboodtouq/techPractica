package com.spring.techpractica.ui.rest.Controller.Admin.System.UpdateSystem;

import jakarta.validation.constraints.NotBlank;

public record UpdateSystemRequest(@NotBlank String name) {
}
