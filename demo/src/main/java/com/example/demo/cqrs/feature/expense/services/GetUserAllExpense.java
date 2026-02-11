package com.example.demo.cqrs.feature.expense.services;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.cqrs.feature.expense.exception.UserNotFoundException;
import com.example.demo.cqrs.feature.expense.presentation.dto.GetAllExpenseByEmailDto;
import com.example.demo.cqrs.port.query.GetAllExpenseByEmailRepo;
import com.example.demo.cqrs.port.query.UserExistsQueryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserAllExpense {
    
    private final GetAllExpenseByEmailRepo expenseByEmailRepo;
    private final UserExistsQueryRepository userExist;

    @Transactional
    public List<GetAllExpenseByEmailDto> getUserAllExpenseByEmailService(@NonNull String email){

        if(!userExist.existsByEmail(email)){
            throw new UserNotFoundException(email);
        }
        return expenseByEmailRepo.GetAllExpense(email);
    }
}
