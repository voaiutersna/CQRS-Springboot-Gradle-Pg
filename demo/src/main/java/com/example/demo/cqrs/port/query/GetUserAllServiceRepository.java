package com.example.demo.cqrs.port.query;


import java.util.List;

import com.example.demo.cqrs.feature.auth.presentation.dto.GetUserDto;

public interface  GetUserAllServiceRepository {
    public abstract List<GetUserDto> GetAllUserOrm();
}
