package com.spring.techpractica.Application.Session.start;

import java.util.UUID;

public record StartSessionCommand(UUID userId, UUID sessionId) {
}