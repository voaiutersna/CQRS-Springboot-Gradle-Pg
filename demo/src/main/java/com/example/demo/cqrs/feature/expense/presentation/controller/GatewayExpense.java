package com.example.demo.cqrs.feature.expense.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cqrs.feature.expense.presentation.dto.CreateUserExpenseDto;
import com.example.demo.cqrs.feature.expense.presentation.dto.GetAllExpenseByEmailDto;
import com.example.demo.cqrs.feature.expense.presentation.dto.GetDetailExpenseByCategoryDto;
import com.example.demo.cqrs.feature.expense.presentation.dto.UpdateExpenseDto;
import com.example.demo.cqrs.feature.expense.services.CreateUserExpense;
import com.example.demo.cqrs.feature.expense.services.DeleteUserExpense;
import com.example.demo.cqrs.feature.expense.services.GetUserAllExpense;
import com.example.demo.cqrs.feature.expense.services.UpdateUserExpense;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class GatewayExpense {

    private final CreateUserExpense CreateExpense;
    private final GetUserAllExpense QueryExpense;
    private final DeleteUserExpense DeleteExpense;
    private final UpdateUserExpense UpdateExpense;
    @PostMapping("/test-expense")
    public UUID GateWayUserExpense(@Valid @RequestBody CreateUserExpenseDto userDto){
        return CreateExpense.createExpense(userDto.getEmail(), userDto.getTotalAmount(), userDto.getNote(), userDto.getCategoryId(), userDto.getPaymentStatus());
    }

    @PostMapping("/email-expense")
    public List<GetAllExpenseByEmailDto> getMethodName(@Valid @RequestBody GetAllExpenseByEmailDto userDto) {
        return QueryExpense.getUserAllExpenseByEmailService(userDto.getemail());
    }

    @PostMapping("/detail-expense")
    public List<GetDetailExpenseByCategoryDto> postMethodName(@Valid @RequestBody GetDetailExpenseByCategoryDto userDto) {
        return QueryExpense.getDetailExpenseByCategoryService(userDto.getcategory_id());
    }

    @GetMapping("/detail-expense/{id}")
    public List<GetDetailExpenseByCategoryDto> getDetailExpenseById(@PathVariable UUID id) {
        return QueryExpense.getDetailExpenseByIdService(id);
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable UUID id) {
        boolean deleted = DeleteExpense.deleteExpense(id);
        if (deleted) {
            return ResponseEntity.ok("Successfully deleted");
        }
        return ResponseEntity.status(404).body("Not found, Please Try again");
    }

    @PatchMapping("/expense/{id}")
    public List<UpdateExpenseDto> updateExpenseById(@PathVariable UUID id, @RequestBody UpdateExpenseDto dto) {
        return UpdateExpense.updateExpense(id, dto.getTotalAmount(), dto.getNote(), dto.getCategoryId(), dto.getPaymentStatus());
    }

}
