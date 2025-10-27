package com.spring.techpractica.ui.rest.Shared;

import lombok.Builder;

import java.time.Instant;

@Builder
public record StandardErrorResponse(
        Instant timestamp,
        int status,
        String message,
        String code
) {
}