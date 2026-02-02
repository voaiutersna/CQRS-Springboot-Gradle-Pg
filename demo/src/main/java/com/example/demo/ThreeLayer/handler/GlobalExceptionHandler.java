package com.example.demo.ThreeLayer.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
        .forEach(error->{
            String ErrorField = error.getField();
            String ErrorMessage = error.getDefaultMessage();
            errors.put(ErrorField, ErrorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResponseStatusException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message-error",ex.getReason());

        return ResponseEntity.badRequest().body(errors);
    }
}
