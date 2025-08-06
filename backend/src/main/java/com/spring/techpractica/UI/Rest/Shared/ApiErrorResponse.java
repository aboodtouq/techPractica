package com.spring.techpractica.UI.Rest.Shared;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String code
) {}