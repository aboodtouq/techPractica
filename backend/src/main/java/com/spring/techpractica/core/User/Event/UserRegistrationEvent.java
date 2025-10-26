package com.spring.techpractica.core.User.Event;

import java.util.UUID;

public record UserRegistrationEvent(UUID userId, String email, String name) {
}
