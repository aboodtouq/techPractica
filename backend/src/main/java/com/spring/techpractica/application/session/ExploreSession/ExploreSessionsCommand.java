package com.spring.techpractica.application.session.ExploreSession;

import java.util.Optional;
import java.util.UUID;

public record ExploreSessionsCommand(Optional<UUID> userId, int page, int size) {
}
