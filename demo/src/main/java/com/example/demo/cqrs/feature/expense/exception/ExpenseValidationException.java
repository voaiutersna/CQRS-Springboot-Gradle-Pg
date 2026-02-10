package com.example.demo.cqrs.feature.expense.exception;
public class ExpenseValidationException extends RuntimeException {

    public ExpenseValidationException(String message) {
        super(message);
    }
}
