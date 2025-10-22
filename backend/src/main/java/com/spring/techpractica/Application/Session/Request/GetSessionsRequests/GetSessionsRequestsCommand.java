package com.spring.techpractica.Application.Session.Request.GetSessionsRequests;

import java.util.UUID;

public record GetSessionsRequestsCommand(UUID userId,
                                         UUID sessionId) {
}
