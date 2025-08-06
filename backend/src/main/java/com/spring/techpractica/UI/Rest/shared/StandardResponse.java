package com.spring.techpractica.UI.Rest.shared;

import lombok.Builder;

@Builder
public record StandardResponse<T>(T data, String message, String state) {
}
