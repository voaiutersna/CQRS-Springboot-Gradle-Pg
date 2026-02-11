package com.example.demo.cqrs.feature.expense.services;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.cqrs.feature.expense.exception.ExpenseValidationException;
import com.example.demo.cqrs.feature.expense.presentation.dto.UpdateExpenseDto;
import com.example.demo.cqrs.port.command.UserAddExpense;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserExpense {
    private final UserAddExpense expenseUpdateCommand;

    @Transactional
    public List<UpdateExpenseDto> updateExpense(@NonNull UUID id, Double total_amount, String note, UUID category_id, Integer payment_status) {
        if (total_amount != null && total_amount < 0) {
            throw new ExpenseValidationException("total_amount must not be negative");
        }
        return expenseUpdateCommand.updateExpenseById(id, total_amount, note, category_id, payment_status);
    }
}
