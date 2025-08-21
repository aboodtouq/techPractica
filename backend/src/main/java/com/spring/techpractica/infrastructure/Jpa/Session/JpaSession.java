package com.spring.techpractica.infrastructure.Jpa.Session;

import com.spring.techpractica.Core.Session.Entity.Session;
import org.graalvm.nativeimage.c.struct.RawPointerTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@RawPointerTo
public interface JpaSession extends JpaRepository<Session, UUID> {
}
