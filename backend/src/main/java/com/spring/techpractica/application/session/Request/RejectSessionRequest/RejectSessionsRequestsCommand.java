package com.spring.techpractica.application.session.Request.RejectSessionRequest;

import java.util.UUID;

public record RejectSessionsRequestsCommand(UUID ownerId,
                                            UUID sessionId,
                                            UUID requestId) {
}
