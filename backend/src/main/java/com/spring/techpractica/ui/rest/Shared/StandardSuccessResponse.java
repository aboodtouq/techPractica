package com.spring.techpractica.ui.rest.Shared;

import lombok.Builder;

@Builder
public record StandardSuccessResponse<T>(T data,
                                         int status,
                                         String message) {
}