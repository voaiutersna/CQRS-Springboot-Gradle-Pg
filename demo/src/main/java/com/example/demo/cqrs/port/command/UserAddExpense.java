package com.example.demo.cqrs.port.command;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;

import com.example.demo.cqrs.feature.expense.presentation.dto.UpdateExpenseDto;

public interface  UserAddExpense {

    public abstract UUID createExpenseUser(@NonNull String email, double total_amount, String note, @NonNull UUID category_id, @NonNull int payment_status);

    public abstract boolean deleteExpenseById(@NonNull UUID id);

    public abstract List<UpdateExpenseDto> updateExpenseById(@NonNull UUID id, Double total_amount, String note, UUID category_id, Integer payment_status);
}
