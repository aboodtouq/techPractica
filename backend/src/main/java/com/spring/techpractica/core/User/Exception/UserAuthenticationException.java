package com.spring.techpractica.core.User.Exception;

public class UserAuthenticationException extends RuntimeException {
    public UserAuthenticationException(String message) {
        super(message);
    }
}
