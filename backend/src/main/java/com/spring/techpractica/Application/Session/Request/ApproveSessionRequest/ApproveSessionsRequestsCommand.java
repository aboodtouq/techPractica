package com.spring.techpractica.Application.Session.Request.ApproveSessionRequest;

import java.util.UUID;

public record ApproveSessionsRequestsCommand(UUID ownerId,
                                             UUID sessionId,
                                             UUID requestId) {
}
