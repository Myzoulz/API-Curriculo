package com.myzoul.curriculo.service;

import com.myzoul.curriculo.model.UserEnt;
import com.myzoul.curriculo.model.dto.LoginRequestDto;
import com.myzoul.curriculo.model.dto.RegisterRequestDto;
import com.myzoul.curriculo.model.dto.ResponseDto;
import com.myzoul.curriculo.repository.UserRepository;
import com.myzoul.curriculo.infra.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public ResponseDto login(LoginRequestDto body) {
        UserEnt user = repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!passwordEncoder.matches(body.senha(), user.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }
        String token = tokenService.generateToken(user);
        return new ResponseDto(user.getEmail(), token);
    }

    public ResponseDto register(RegisterRequestDto body) {
        if (repository.findByEmail(body.email()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        UserEnt newUser = new UserEnt();
        newUser.setEmail(body.email());
        newUser.setSenha(passwordEncoder.encode(body.senha()));
        newUser.setCpf(body.cpf().toString());
        repository.save(newUser);
        String token = tokenService.generateToken(newUser);
        return new ResponseDto(newUser.getEmail(), token);
    }
}