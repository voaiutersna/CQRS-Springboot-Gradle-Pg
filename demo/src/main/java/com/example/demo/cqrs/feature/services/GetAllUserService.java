package com.example.demo.cqrs.feature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.cqrs.feature.auth.presentation.dto.GetUserDto;
import com.example.demo.cqrs.port.query.GetUserAllServiceRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAllUserService {

    private final GetUserAllServiceRepository GetUsers;
    
    @Transactional
    public List<GetUserDto> GetUserService(){
        return GetUsers.GetAllUserOrm();
    }
}
