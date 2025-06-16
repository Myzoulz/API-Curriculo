package com.myzoul.curriculo.controller;

import com.myzoul.curriculo.model.UserEnt;
import com.myzoul.curriculo.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        UserEnt user = (UserEnt) authentication.getPrincipal();
        UserDto dto = new UserDto(user.getEmail(), user.getCpf());
        return ResponseEntity.ok(dto);
    }
}