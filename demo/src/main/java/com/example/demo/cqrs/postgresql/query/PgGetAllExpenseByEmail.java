package com.example.demo.cqrs.postgresql.query;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.cqrs.feature.expense.presentation.dto.GetAllExpenseByEmailDto;
import com.example.demo.cqrs.port.query.GetAllExpenseByEmailRepo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PgGetAllExpenseByEmail implements GetAllExpenseByEmailRepo {
    
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<GetAllExpenseByEmailDto> GetAllExpense(@NonNull String email){

        final String rawSQL = "SELECT expense.total_amount, expense.category_id, expense.pay_status_id, expense.note, expense.created_at, expense.updated_at FROM expense WHERE user_id = (SELECT id FROM users WHERE email = ?)";



        return jdbcTemplate.query(rawSQL, (row,index)->new GetAllExpenseByEmailDto(
            email,
            row.getDouble("total_amount"),
            row.getString("note"),
            row.getObject("category_id", java.util.UUID.class),
            row.getInt("pay_status_id"),
            row.getTimestamp("created_at"),
            row.getTimestamp("updated_at")
        ), email);
    }

}
