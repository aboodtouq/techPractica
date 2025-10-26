package com.spring.techpractica.application.session.GetSessions.GetSessionsBySystem;

import java.util.UUID;

public record GetSessionsBySystemCommand(UUID systemId,
                                         int size,
                                         int page) {
}
