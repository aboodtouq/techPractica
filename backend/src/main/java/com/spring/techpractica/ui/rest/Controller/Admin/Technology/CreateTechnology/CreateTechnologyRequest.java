package com.spring.techpractica.ui.rest.Controller.Admin.Technology.CreateTechnology;

import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record CreateTechnologyRequest(@NotBlank String name, List<String> fieldNames) {
}
