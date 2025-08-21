package com.spring.techpractica.Application.Session.CreateSession;

import java.util.UUID;

public record CreateSessionCommand (UUID userId, String name,
                                    String description, boolean isPrivate,
                                    String system, String field, String technology) {
}
