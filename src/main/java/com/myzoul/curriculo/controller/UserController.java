package com.myzoul.curriculo.controller;

import com.myzoul.curriculo.infra.security.CurrentUser;
import com.myzoul.curriculo.model.UserEnt;
import com.myzoul.curriculo.model.dto.UserDto;
import com.myzoul.curriculo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(@CurrentUser UserEnt user) {
        return ResponseEntity.ok(userService.toDto(user));
    }
}