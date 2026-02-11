package com.example.demo.cqrs.feature.expense.services;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.cqrs.feature.expense.exception.UserNotFoundException;
import com.example.demo.cqrs.feature.expense.presentation.dto.GetAllExpenseByEmailDto;
import com.example.demo.cqrs.feature.expense.presentation.dto.GetDetailExpenseByCategoryDto;
import com.example.demo.cqrs.port.query.GetAllExpenseRepo;
import com.example.demo.cqrs.port.query.UserExistsQueryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserAllExpense {
    
    private final GetAllExpenseRepo expenseRepo;
    private final UserExistsQueryRepository userExist;

    @Transactional
    public List<GetAllExpenseByEmailDto> getUserAllExpenseByEmailService(@NonNull String email){

        if(!userExist.existsByEmail(email)){
            throw new UserNotFoundException(email);
        }
        return expenseRepo.GetAllExpenseByemail(email);
    }

    @Transactional
    public List<GetDetailExpenseByCategoryDto> getDetailExpenseByCategoryService(@NonNull UUID category_id){

        return expenseRepo.GetDetailExpense(category_id);
    }

    @Transactional
    public List<GetDetailExpenseByCategoryDto> getDetailExpenseByIdService(@NonNull UUID id){
        return expenseRepo.GetDetailByIdExpense(id);
    }


}
