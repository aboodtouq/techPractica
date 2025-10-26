package com.spring.techpractica.Application.Session.Request.RejectSessionRequest;

import java.util.UUID;

public record RejectSessionsRequestsCommand(UUID ownerId,
                                            UUID sessionId,
                                            UUID requestId) {
}
