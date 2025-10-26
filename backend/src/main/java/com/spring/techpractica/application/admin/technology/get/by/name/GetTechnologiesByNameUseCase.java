package com.spring.techpractica.application.admin.technology.get.by.name;

import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.Technology.Entity.Technology;
import com.spring.techpractica.core.Technology.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTechnologiesByNameUseCase {
    private final TechnologyRepository technologyRepository;

    public List<Technology> execute(GetTechnologiesByNameCommand command) {
        List<Technology> technologies = technologyRepository.findAllByNames(command.names());
        if (technologies.size() != command.names().size()) {
            throw new ResourcesNotFoundException(command.names());
        }
        return technologies;
    }
}
