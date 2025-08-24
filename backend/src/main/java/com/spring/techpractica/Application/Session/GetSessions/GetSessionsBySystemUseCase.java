package com.spring.techpractica.Application.Session.GetSessions;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.System.Entity.System;
import com.spring.techpractica.Core.System.SystemRepository;
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
        System system = systemRepository.findSystemByName(command.systemName())
                .orElseThrow(() -> new ResourcesNotFoundException(command.systemName()));

        return sessionRepository.getSessionsBySystems(
                List.of(system),
                PageRequest.of(command.sizeOfPage(), command.pageNumber())
        );
    }
}
