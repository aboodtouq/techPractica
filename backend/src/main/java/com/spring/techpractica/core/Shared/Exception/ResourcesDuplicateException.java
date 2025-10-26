package com.spring.techpractica.core.Shared.Exception;

import java.util.UUID;

public class ResourcesDuplicateException extends RuntimeException {

    public ResourcesDuplicateException(String name) {
        super(String.format("Entity has already been created with name: %s", name));
    }

    public ResourcesDuplicateException(UUID userId, UUID requirementId) {
        super(String.format("Request has already found with userId: %s requirementId: %s", userId.toString(), requirementId.toString()));
    }
}
