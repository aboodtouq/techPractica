package com.spring.techpractica.Application.Session.GetSessions;

import java.util.UUID;

public record GetSessionsBySystemCommand(UUID systemId,
                                         int sizeOfPage,
                                         int pageNumber) {
}
