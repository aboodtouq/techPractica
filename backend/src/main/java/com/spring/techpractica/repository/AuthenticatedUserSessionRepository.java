package com.spring.techpractica.repository;

import com.spring.techpractica.model.entity.AuthenticatedUserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticatedUserSessionRepository extends JpaRepository<AuthenticatedUserSession, Long> {


    Optional<AuthenticatedUserSession> findByUserUserIdAndSessionSessionId(Long userId, Long sessionId);
}
