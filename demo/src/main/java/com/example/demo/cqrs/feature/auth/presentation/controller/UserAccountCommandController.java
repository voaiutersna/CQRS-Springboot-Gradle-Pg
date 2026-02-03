package com.example.demo.cqrs.feature.auth.presentation.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cqrs.feature.auth.presentation.dto.CreateUserAccountDto;
import com.example.demo.cqrs.feature.services.CreateUserAccountCommandHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserAccountCommandController {

    private final CreateUserAccountCommandHandler createUserAccountCommandHandler;

    @PostMapping("/sign-up")
    public UUID postMethodName(@Valid @RequestBody CreateUserAccountDto UserDto) {
        return createUserAccountCommandHandler.handle(UserDto.getName(),UserDto.getEmail(),UserDto.getPassword());
    }
    
}
