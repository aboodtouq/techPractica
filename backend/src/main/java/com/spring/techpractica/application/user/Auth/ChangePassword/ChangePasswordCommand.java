package com.spring.techpractica.application.user.Auth.ChangePassword;

import java.util.UUID;

public record ChangePasswordCommand(UUID id, String password) {
}
