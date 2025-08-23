package com.spring.techpractica.Application.Session.ExploreSession;

import java.util.UUID;

public record ExploreSessionsCommand(UUID userId, int page, int size) {
}
