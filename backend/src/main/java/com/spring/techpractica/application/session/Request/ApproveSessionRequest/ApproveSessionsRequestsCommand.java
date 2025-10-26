package com.spring.techpractica.application.session.Request.ApproveSessionRequest;

import java.util.UUID;

public record ApproveSessionsRequestsCommand(UUID ownerId,
                                             UUID sessionId,
                                             UUID requestId) {
}
