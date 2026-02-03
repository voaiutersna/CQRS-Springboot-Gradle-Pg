package com.example.demo.cqrs.port.command;

import java.util.UUID;

import org.jspecify.annotations.NonNull;

public interface  UserAccountsCommandServiceRepository {
    public abstract UUID createUserByEmail(@NonNull String name, @NonNull String email, @NonNull String password);
}