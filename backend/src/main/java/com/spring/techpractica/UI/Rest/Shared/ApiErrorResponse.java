package com.spring.techpractica.UI.Rest.Shared;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String message,
        String code
) {
}