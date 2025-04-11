package com.spring.techpractica.controller;

import com.spring.techpractica.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HashMap<Object, String>> authenticationException(AuthenticationException e) {

        HashMap<Object, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
