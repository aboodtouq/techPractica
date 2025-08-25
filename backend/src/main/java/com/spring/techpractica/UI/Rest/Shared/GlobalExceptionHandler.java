package com.spring.techpractica.UI.Rest.Shared;

import com.spring.techpractica.Core.Shared.Exception.ResourcesDuplicateException;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.UI.Rest.Shared.Exception.InvalidPageRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StandardErrorResponse.builder()
                        .timestamp(Instant.now())
                        .message(e.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .code("ILLEGAL_ARGUMENT")
                        .build());
    }

    @ExceptionHandler(InvalidPageRequestException.class)
    public ResponseEntity<StandardErrorResponse> handleInvalidPageRequestException(InvalidPageRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StandardErrorResponse.builder()
                        .timestamp(Instant.now())
                        .message(e.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .code("BAD_REQUEST")
                        .build());
    }

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> handleResourcesNotFoundException(ResourcesNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(StandardErrorResponse.builder()
                        .timestamp(Instant.now())
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .code("RESOURCE_NOT_FOUND")
                        .build());
    }

    @ExceptionHandler(ResourcesDuplicateException.class)
    public ResponseEntity<StandardErrorResponse> handleResourcesDuplicateException(ResourcesDuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(StandardErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .code("RESOURCE_ALREADY_EXISTS")
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String fieldMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " +
                                   Optional.ofNullable(fieldError.getDefaultMessage()).orElse("Invalid value"))
                .collect(Collectors.joining(", "));

        StandardErrorResponse response = StandardErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed: " + fieldMessages)
                .code("VALIDATION_ERROR")
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}
