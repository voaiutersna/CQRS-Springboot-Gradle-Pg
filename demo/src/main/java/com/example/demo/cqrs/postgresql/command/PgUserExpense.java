package com.example.demo.cqrs.postgresql.command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.cqrs.feature.expense.presentation.dto.UpdateExpenseDto;
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

    @Override
    public boolean deleteExpenseById(@NonNull UUID id) {
        final String rawSQL = "DELETE FROM expense WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(rawSQL, id); // return 1 or 0
        return rowsAffected > 0;
    }

    @Override
    public List<UpdateExpenseDto> updateExpenseById(@NonNull UUID id, Double total_amount, String note, UUID category_id, Integer payment_status) {
        List<String> setClauses = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (total_amount != null) {
            setClauses.add("total_amount = ?");
            params.add(total_amount);
        }
        if (note != null) {
            setClauses.add("note = ?");
            params.add(note);
        }
        if (category_id != null) {
            setClauses.add("category_id = ?");
            params.add(category_id);
        }
        if (payment_status != null) {
            setClauses.add("pay_status_id = ?");
            params.add(payment_status);
        }

        setClauses.add("updated_at = NOW()");
        params.add(id);

        final String rawSQL = "UPDATE expense SET " + String.join(", ", setClauses) + " WHERE id = ? RETURNING total_amount, note, category_id, pay_status_id, created_at, updated_at";
        return jdbcTemplate.query(rawSQL, (row, index) -> new UpdateExpenseDto(
            row.getDouble("total_amount"),
            row.getString("note"),
            row.getObject("category_id", UUID.class),
            row.getInt("pay_status_id"),
            row.getTimestamp("created_at"),
            row.getTimestamp("updated_at")
        ), params.toArray());
    }

}

//Database ส่ง ResultSet กลับมา (อาจมี 1 row หรือหลาย rows)
// -> Spring JDBC loop แต่ละ row โดยอัตโนมัติ แล้วเรียก lambda function
//row.getDouble("total_amount"),    // ดึงค่า total_amount จาก row ที่กำลัง loop


//Mock Data and Preview Syntax
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
