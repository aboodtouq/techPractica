package com.spring.techpractica.application.session.Request.createrequest;

import java.util.UUID;

public record CreateRequestCommand(UUID userId,
                                   UUID requirementId,
                                   String brief) {
}
