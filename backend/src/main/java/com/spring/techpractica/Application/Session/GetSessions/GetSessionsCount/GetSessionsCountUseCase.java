package com.spring.techpractica.Application.Session.GetSessions.GetSessionsCount;

import com.spring.techpractica.Application.Session.GetSessions.GetSessionsBySystem.GetSessionsBySystemCommand;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.System.Entity.System;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetSessionsCountUseCase {
    private final SessionRepository sessionRepository;

    public long execute() {
        return sessionRepository.getSessionsCount();
    }
}
