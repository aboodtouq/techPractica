package com.spring.techpractica.core.RequirementTechnology;

import com.spring.techpractica.core.Requirement.Entity.Requirement;
import com.spring.techpractica.core.RequirementTechnology.Entity.RequirementTechnology;
import com.spring.techpractica.core.RequirementTechnology.Model.RequirementTechnologyId;
import com.spring.techpractica.core.Technology.Entity.Technology;
import org.springframework.stereotype.Component;

@Component
public class RequirementTechnologyFactory {
    public RequirementTechnology create(Requirement requirement, Technology technology) {
        return RequirementTechnology.builder()
                .id(new RequirementTechnologyId(requirement.getId(), technology.getId()))
                .requirement(requirement)
                .technology(technology)
                .build();
    }
}
