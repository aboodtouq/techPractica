package com.spring.techpractica.UI.Rest.Resources.RequirementTechnology;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RequirementTechnologyResources {

    private UUID id;
    private String technologyName;

}