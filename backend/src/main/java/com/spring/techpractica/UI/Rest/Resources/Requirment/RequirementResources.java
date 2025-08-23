package com.spring.techpractica.UI.Rest.Resources.Requirment;

import com.spring.techpractica.Core.Requirement.Entity.Requirement;
import com.spring.techpractica.Core.RequirementTechnology.Entity.RequirementTechnology;
import com.spring.techpractica.UI.Rest.Resources.Field.FieldResources;
import com.spring.techpractica.UI.Rest.Resources.Technology.TechnologyCollection;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
public class RequirementResources {

    private final FieldResources field;
    private final TechnologyCollection technologies;

    public RequirementResources(Requirement requirement) {
        this.field = new FieldResources(requirement.getField().getId(),
                requirement.getField().getName());

        this.technologies = new TechnologyCollection(
                requirement.getRequirementTechnologies()
                        .stream()
                        .map(RequirementTechnology::getTechnology)
                        .collect(Collectors.toList())
        );
    }
}
