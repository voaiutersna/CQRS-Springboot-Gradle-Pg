package com.example.demo.cqrs.feature.expense.presentation.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cqrs.feature.expense.presentation.dto.CreateUserExpenseDto;
import com.example.demo.cqrs.feature.expense.services.CreateUserExpense;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GatewayExpense {

    private final CreateUserExpense expenseService;

    @PostMapping("/test-expense")
    public UUID GateWayUserExpense(@Valid @RequestBody CreateUserExpenseDto userDto){
        return expenseService.createExpense(userDto.getEmail(), userDto.getTotalAmount(), userDto.getNote(), userDto.getCategoryId(), userDto.getPaymentStatus());
    }


}
