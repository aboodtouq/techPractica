package com.spring.techpractica.core.Shared.Exception;

public class DuplicateTechnologyException extends RuntimeException {
    public DuplicateTechnologyException(String fieldName) {
        super("Duplicate technologies found for field: " + fieldName);
    }
}