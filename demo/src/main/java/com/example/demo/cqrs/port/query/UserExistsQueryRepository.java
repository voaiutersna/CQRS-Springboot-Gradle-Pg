package com.example.demo.cqrs.port.query;

import org.jspecify.annotations.NonNull;

public interface UserExistsQueryRepository {
    boolean existsByEmail(@NonNull String email);
}
