package com.spring.techpractica.application.session.Request.GetSessionsRequests;

import java.util.UUID;

public record GetSessionsRequestsCommand(UUID userId,
                                         UUID sessionId) {
}
