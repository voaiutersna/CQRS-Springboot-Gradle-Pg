package com.example.demo.cqrs.postgresql.command;

import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.cqrs.port.command.UserAddExpense;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PgUserExpense implements UserAddExpense {
    
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UUID createExpenseUser(@NonNull String email, double total_amount, String note, @NonNull UUID category_id, @NonNull int payment_status){
        final String RawSQL = "INSERT INTO expense (user_id, category_id, total_amount, note, pay_status_id) VALUES ((SELECT id FROM users WHERE email = ?),?,?,?,?) RETURNING id";
        final UUID id = jdbcTemplate.queryForObject(RawSQL, UUID.class, email, category_id, total_amount, note, payment_status);
        return id;
     }

}

// INSERT INTO expense (user_id, category_id, total_amount, note, pay_status_id) VALUES
//   (
//     (SELECT id FROM users WHERE email = 'somchai@example.com'),
//     (SELECT id FROM category WHERE name = 'อาหาร' LIMIT 1),
//     550.00,
//     'ข้าวเที่ยง ร้านข้าวแกง',
//     2  -- paid
//   ),
//   (
//     (SELECT id FROM users WHERE email = 'somchai@example.com'),
//     (SELECT id FROM category WHERE name = 'ค่าเดินทาง' LIMIT 1),
//     5500.00,
//     'เติมน้ำมัน Shell',
//     1  -- unpaid
//   );
