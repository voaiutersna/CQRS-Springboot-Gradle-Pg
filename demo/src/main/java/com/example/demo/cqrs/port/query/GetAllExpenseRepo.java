package com.example.demo.cqrs.port.query;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;

import com.example.demo.cqrs.feature.expense.presentation.dto.GetAllExpenseByEmailDto;
import com.example.demo.cqrs.feature.expense.presentation.dto.GetDetailExpenseByCategoryDto;

public interface  GetAllExpenseRepo {
    public abstract List<GetAllExpenseByEmailDto>  GetAllExpenseByemail(@NonNull String email);
    public abstract List<GetDetailExpenseByCategoryDto>  GetDetailExpense(@NonNull UUID category_id);
    public abstract List<GetDetailExpenseByCategoryDto>  GetDetailByIdExpense(@NonNull UUID id); 
}
