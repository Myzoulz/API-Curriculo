package com.myzoul.curriculo.controller;

import com.myzoul.curriculo.model.dto.LoginRequestDto;
import com.myzoul.curriculo.model.dto.RegisterRequestDto;
import com.myzoul.curriculo.model.dto.ResponseDto;
import com.myzoul.curriculo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto body) {
        try {
            return ResponseEntity.ok(authService.login(body));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody RegisterRequestDto body) {
        try {
            return ResponseEntity.ok(authService.register(body));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}