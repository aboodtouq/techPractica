package com.spring.techpractica.core.User.Event;

import java.util.UUID;

public record ResetPasswordEvent(UUID id, String email, String name) {
}