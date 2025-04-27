package com.spring.techpractica.repository;

import com.spring.techpractica.model.UserSessionId;
import com.spring.techpractica.model.entity.AuthenticatedUserSession;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticatedUserSessionRepository extends JpaRepository<AuthenticatedUserSession, Long> {


    Optional<AuthenticatedUserSession> findByUserUserIdAndSessionSessionId(Long userId, Long sessionId);
}
