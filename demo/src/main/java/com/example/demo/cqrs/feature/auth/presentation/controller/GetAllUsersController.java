package com.example.demo.cqrs.feature.auth.presentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cqrs.feature.auth.presentation.dto.GetUserDto;
import com.example.demo.cqrs.feature.auth.services.GetAllUserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class GetAllUsersController {
    
    private final GetAllUserService GetUSER;
    @GetMapping("/users")
    public List<GetUserDto> getMethodName() {
        return GetUSER.GetUserService();
    }
    
}
