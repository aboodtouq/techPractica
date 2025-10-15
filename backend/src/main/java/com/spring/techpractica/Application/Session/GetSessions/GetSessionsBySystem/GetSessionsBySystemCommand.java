package com.spring.techpractica.Application.Session.GetSessions.GetSessionsBySystem;

import java.util.UUID;

public record GetSessionsBySystemCommand(UUID systemId,
                                         int size,
                                         int page) {
}
