package com.spring.techpractica.application.session.GetSessions.UserSessions;

import java.util.UUID;

public record GetUserSessionCommand(UUID userId, int size, int page) {
}
