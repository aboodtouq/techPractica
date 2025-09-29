package com.spring.techpractica.infrastructure.Jpa.Session;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionStatus;
import com.spring.techpractica.Core.System.Entity.System;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaSession extends JpaRepository<Session, UUID>, JpaSpecificationExecutor<Session> {


    @Query("SELECT DISTINCT s FROM Session s JOIN s.systems sys " +
            "WHERE sys IN :systems AND s.status NOT IN :excludedStatuses")
    Page<Session> findAllBySystemsExcludeStatuses(
            @Param("systems") List<System> systems,
            @Param("excludedStatuses") List<SessionStatus> excludedStatuses,
            Pageable pageable
    );

    @Query("SELECT s FROM Session s WHERE s.status NOT IN :excludedStatuses")
    Page<Session> findAllExcludeStatuses(@Param("excludedStatuses") List<SessionStatus> excludedStatuses, Pageable pageable);



    @Query("SELECT s FROM Session s " +
            "JOIN s.members m " +
            "WHERE m.user.id = :userId")
    Page<Session> findAllByUserId(@Param("userId") UUID userId, Pageable pageable);


    @Query("SELECT COUNT(s) FROM Session s")
    long getAllSessionsCount();
}