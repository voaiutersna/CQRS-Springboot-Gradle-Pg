package com.example.demo.cqrs.postgresql.query;

import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.cqrs.port.query.UserExistsQueryRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PgUserExistsQuery implements UserExistsQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsByEmail(@NonNull String email) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM users WHERE email = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, email));
    }
}
