package com.spring.techpractica.Core.Session;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Shared.BaseRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SessionRepository extends BaseRepository<Session> {
    List<Session> exploreSessions(Pageable pageable);
}
