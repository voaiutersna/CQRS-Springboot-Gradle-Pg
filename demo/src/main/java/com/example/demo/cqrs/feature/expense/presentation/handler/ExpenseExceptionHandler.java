package com.example.demo.cqrs.feature.expense.presentation.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.cqrs.feature.expense.exception.ExpenseValidationException;
import com.example.demo.cqrs.feature.expense.exception.UserNotFoundException;

@RestControllerAdvice
public class ExpenseExceptionHandler {

    @ExceptionHandler(ExpenseValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidation(ExpenseValidationException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
}
