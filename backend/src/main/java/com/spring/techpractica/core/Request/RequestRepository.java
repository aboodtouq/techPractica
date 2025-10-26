package com.spring.techpractica.core.Request;

import com.spring.techpractica.core.Request.Entity.Request;
import com.spring.techpractica.core.Shared.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface RequestRepository extends BaseRepository<Request> {
    boolean existsByUserIdAndRequirementId(UUID id, UUID requirementId);

    Optional<Request> findByIdAndSessionId(UUID requestId, UUID sessionId);
}
