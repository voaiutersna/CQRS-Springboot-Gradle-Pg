package com.example.demo.cqrs.port.query;

import java.util.List;

import org.jspecify.annotations.NonNull;

import com.example.demo.cqrs.feature.expense.presentation.dto.GetAllExpenseByEmailDto;

public interface  GetAllExpenseByEmailRepo {
    public abstract List<GetAllExpenseByEmailDto>  GetAllExpense(@NonNull String email);
}
