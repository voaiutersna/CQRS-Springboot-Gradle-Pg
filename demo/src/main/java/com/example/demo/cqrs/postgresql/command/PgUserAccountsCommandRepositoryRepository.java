package com.example.demo.cqrs.postgresql.command;


import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.cqrs.port.command.UserAccountsCommandServiceRepository;

import lombok.RequiredArgsConstructor;
;

@Service
@RequiredArgsConstructor
public class PgUserAccountsCommandRepositoryRepository implements UserAccountsCommandServiceRepository {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public UUID createUserByEmail(@NonNull String name, @NonNull String email, @NonNull String password){
        final String CommandSql = "INSERT INTO users (id, name, email, password, created_at) VALUES (gen_random_uuid(), ?, ?, ?,CURRENT_TIMESTAMP) RETURNING id";

        final UUID id = jdbcTemplate.queryForObject(CommandSql, UUID.class, name, email, password);
        return id;
    }
}
