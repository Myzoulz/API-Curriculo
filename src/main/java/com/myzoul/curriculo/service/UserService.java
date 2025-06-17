package com.myzoul.curriculo.service;

import com.myzoul.curriculo.model.UserEnt;
import com.myzoul.curriculo.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto toDto(UserEnt user) {
        return new UserDto(user.getEmail(), user.getCpf());
    }
}