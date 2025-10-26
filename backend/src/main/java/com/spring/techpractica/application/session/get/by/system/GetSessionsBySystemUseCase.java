package com.spring.techpractica.application.session.get.by.system;

import com.spring.techpractica.core.Session.Entity.Session;
import com.spring.techpractica.core.Session.SessionRepository;
import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.System.Entity.System;
import com.spring.techpractica.core.System.SystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetSessionsBySystemUseCase {

    private final SessionRepository sessionRepository;

    private final SystemRepository systemRepository;

    public List<Session> execute(GetSessionsBySystemCommand command) {
        System system = systemRepository.findById(command.systemId())
                .orElseThrow(() -> new ResourcesNotFoundException(command.systemId()));

        return sessionRepository.getSessionsBySystems(
                List.of(system),
                PageRequest.of(command.size(), command.page())
        );
    }
}
