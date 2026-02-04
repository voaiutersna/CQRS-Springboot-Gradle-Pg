package com.example.demo.cqrs.postgresql.query;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.cqrs.feature.auth.presentation.dto.GetUserDto;
import com.example.demo.cqrs.port.query.GetUserAllServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PgGetAllUserAccount implements GetUserAllServiceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<GetUserDto> GetAllUserOrm() {
        final String sql = "SELECT name, email, password FROM users";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new GetUserDto(
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password")
        ));
    }
}
