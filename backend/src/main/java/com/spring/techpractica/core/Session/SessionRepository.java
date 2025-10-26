package com.spring.techpractica.core.Session;

import com.spring.techpractica.core.Request.Entity.Request;
import com.spring.techpractica.core.Session.Entity.Session;
import com.spring.techpractica.core.Shared.BaseRepository;
import com.spring.techpractica.core.System.Entity.System;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends BaseRepository<Session> {
    List<Session> exploreSessions(Pageable pageable);

    List<Session> getSessionsBySystems(List<System> systems, Pageable pageable);

    List<Session> getSessionsByFieldId(UUID fieldId);

    List<Session> getSessionsBySystemId(UUID systemId);

    List<Session> getSessionsByTechnologyId(UUID technologyId);

    List<Session> findAllWithSpecification(Specification<Session> specification, Pageable pageable);

    Page<Session> getSessionsByUser(UUID userID, Pageable pageable);

    List<Request> getRequestsBySession(UUID sessionID);

    long getSessionsCount();

    long getUserSessionsCount(UUID userID);
}