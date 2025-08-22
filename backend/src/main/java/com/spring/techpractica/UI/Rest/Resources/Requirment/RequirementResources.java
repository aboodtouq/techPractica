package com.spring.techpractica.UI.Rest.Resources.Requirment;

import com.spring.techpractica.Core.Requirement.Entity.Requirement;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RequirementResources {

    private final String field;
    private final List<String> technologies;

    public RequirementResources(Requirement requirement) {
        this.field = requirement.getField().getName();

        this.technologies = requirement.getRequirementTechnologies()
                .stream()
                .map(reqTech -> reqTech.getTechnology().getName())
                .collect(Collectors.toList());
    }
}
