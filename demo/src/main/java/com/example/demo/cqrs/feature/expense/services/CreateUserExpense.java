package com.example.demo.cqrs.feature.expense.services;

import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.cqrs.feature.expense.exception.ExpenseValidationException;
import com.example.demo.cqrs.feature.expense.exception.UserNotFoundException;
import com.example.demo.cqrs.port.command.UserAddExpense;
import com.example.demo.cqrs.port.query.UserExistsQueryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserExpense {
    private final UserAddExpense addExpense;
    private final UserExistsQueryRepository userExistsQuery;

    @Transactional
    public UUID createExpense(@NonNull String email, double total_amount, String note, @NonNull UUID category_id, @NonNull int payment_status) {
        if (total_amount < 0) {
            throw new ExpenseValidationException("total_amount must not be negative");
        }

        if (!userExistsQuery.existsByEmail(email)) {
            throw new UserNotFoundException(email);
        }

        return addExpense.createExpenseUser(email, total_amount, note, category_id, payment_status);
    }
}
