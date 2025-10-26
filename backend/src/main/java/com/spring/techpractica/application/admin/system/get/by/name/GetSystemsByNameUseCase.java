package com.spring.techpractica.application.admin.system.get.by.name;

import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.System.Entity.System;
import com.spring.techpractica.core.System.SystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetSystemsByNameUseCase {
    private final SystemRepository systemRepository;

    public List<System> execute(GetSystemsByNameCommand command) {
        List<System> systems = systemRepository.findAllByNames(command.systemsNames());

        if (systems.size() != command.systemsNames().size()) {
            throw new ResourcesNotFoundException(command.systemsNames());
        }

        return systems;
    }
}
