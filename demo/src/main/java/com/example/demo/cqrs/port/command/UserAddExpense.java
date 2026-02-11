package com.example.demo.cqrs.port.command;

import java.util.UUID;

import org.jspecify.annotations.NonNull;

public interface  UserAddExpense {

    public abstract UUID createExpenseUser(@NonNull String email, double total_amount, String note, @NonNull UUID category_id, @NonNull int payment_status);
}
