package com.spring.techpractica.Application.Admin.Technology.DeleteTechnology;

import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesDuplicateException;
import com.spring.techpractica.Core.System.Entity.System;
import com.spring.techpractica.Core.System.SystemRepository;
import com.spring.techpractica.Core.Technology.Entity.Technology;
import com.spring.techpractica.Core.Technology.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteTechnologyUseCase {
    private final TechnologyRepository technologyRepository;

    private final SessionRepository sessionRepository;
    @Transactional
    public void execute(DeleteTechnologyCommand command) {

        Technology technology = technologyRepository.getOrThrowByID(command.technologyId());

        if (!sessionRepository.getSessionsByTechnologyId(command.technologyId()).isEmpty()) {
            throw new ResourcesDuplicateException("session with technology id " + command.technologyId() + " exists");
        }
         technologyRepository.deleteById(technology.getId());
    }
}
