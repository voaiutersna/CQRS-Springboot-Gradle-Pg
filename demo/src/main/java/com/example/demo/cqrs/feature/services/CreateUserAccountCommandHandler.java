package com.example.demo.cqrs.feature.services;

import java.util.UUID;

import com.example.demo.cqrs.port.command.UserAccountsCommandServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserAccountCommandHandler {

    private final UserAccountsCommandServiceRepository userAccountsCommandService;
    @Transactional
    public UUID handle(String name, String email,String password){
        return userAccountsCommandService.createUserByEmail(name, email, password);
    }
}
