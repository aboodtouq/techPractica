package com.spring.techpractica.application.admin.technology.get.by.field;

import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.Technology.Entity.Technology;
import com.spring.techpractica.core.Technology.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTechnologiesByFieldUseCase {
    private final TechnologyRepository technologyRepository;

    public List<Technology> execute(GetTechnologiesByFieldCommand command) {
        List<Technology> technologies = technologyRepository.findAllByFieldId(command.fieldId());
        if (technologies.isEmpty() || technologies == null) {
            throw new ResourcesNotFoundException("No technologies found for field id " + command.fieldId());
        }
        return technologies;
    }
}
