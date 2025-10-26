package com.spring.techpractica.core.User.Exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }

    public EmailAlreadyUsedException() {
        super("Email is already in use.");
    }
}
