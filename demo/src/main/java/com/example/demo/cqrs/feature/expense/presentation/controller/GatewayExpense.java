package com.example.demo.cqrs.feature.expense.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cqrs.feature.expense.presentation.dto.CreateUserExpenseDto;
import com.example.demo.cqrs.feature.expense.presentation.dto.GetAllExpenseByEmailDto;
import com.example.demo.cqrs.feature.expense.services.CreateUserExpense;
import com.example.demo.cqrs.feature.expense.services.GetUserAllExpense;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class GatewayExpense {

    private final CreateUserExpense expenseService;
    private final GetUserAllExpense expenseByEmail;
    @PostMapping("/test-expense")
    public UUID GateWayUserExpense(@Valid @RequestBody CreateUserExpenseDto userDto){
        return expenseService.createExpense(userDto.getEmail(), userDto.getTotalAmount(), userDto.getNote(), userDto.getCategoryId(), userDto.getPaymentStatus());
    }

    @PostMapping("/email-expense")
    public List<GetAllExpenseByEmailDto> getMethodName(@Valid @RequestBody GetAllExpenseByEmailDto userDto) {
        return expenseByEmail.getUserAllExpenseByEmailService(userDto.getEmail());
    }
    


}
