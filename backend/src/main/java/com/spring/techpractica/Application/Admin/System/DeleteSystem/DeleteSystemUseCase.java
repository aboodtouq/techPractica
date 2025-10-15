package com.spring.techpractica.Application.Admin.System.DeleteSystem;

import com.spring.techpractica.Core.Field.Entity.Field;
import com.spring.techpractica.Core.Field.FieldRepository;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesDuplicateException;
import com.spring.techpractica.Core.System.Entity.System;
import com.spring.techpractica.Core.System.SystemRepository;
import com.spring.techpractica.infrastructure.Jpa.System.JpaSystem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DeleteSystemUseCase {
    private final SystemRepository systemRepository;

    private final SessionRepository sessionRepository;

    @Transactional
    public void execute(DeleteSystemCommand command) {

        System system = systemRepository.getOrThrowByID(command.systemId());

        if(!sessionRepository.getSessionsBySystemId(command.systemId()).isEmpty()) {
            throw new ResourcesDuplicateException("session with system id " + command.systemId() + "  exists");
        }
         systemRepository.deleteById(system.getId());
    }
}
