package com.example.demo.cqrs.feature.expense.services;

import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.cqrs.port.command.UserAddExpense;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteUserExpense {
    private final UserAddExpense expenseCommand;

    @Transactional
    public boolean deleteExpense(@NonNull UUID id) {
        return expenseCommand.deleteExpenseById(id);
    }
}
