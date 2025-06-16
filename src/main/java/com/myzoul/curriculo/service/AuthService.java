package com.myzoul.curriculo.service;

import com.myzoul.curriculo.model.UserEnt;
import com.myzoul.curriculo.model.dto.LoginRequestDto;
import com.myzoul.curriculo.model.dto.RegisterRequestDto;
import com.myzoul.curriculo.model.dto.ResponseDto;
import com.myzoul.curriculo.repository.UserRepository;
import com.myzoul.curriculo.infra.security.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.myzoul.curriculo.exception.BusinessException;
import com.myzoul.curriculo.exception.NotFoundException;

import java.util.Collections;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public ResponseDto login(LoginRequestDto body) {
        logger.info("Tentativa de login para o email: {}", body.email());
        UserEnt user = repository.findByEmail(body.email())
                .orElseThrow(() -> {
                    logger.warn("Login falhou: usuário não encontrado para {}", body.email());
                    return new NotFoundException("Credenciais inválidas");
                });
        if (!passwordEncoder.matches(body.senha(), user.getSenha())) {
            logger.warn("Login falhou: senha inválida para {}", body.email());
            throw new BusinessException("Credenciais inválidas");
        }
        String token = tokenService.generateToken(user);
        logger.info("Login bem-sucedido para {}", body.email());
        return new ResponseDto(user.getEmail(), token);
    }

    public ResponseDto register(RegisterRequestDto body) {
        logger.info("Tentativa de registro para o email: {}", body.email());
        if (repository.findByEmail(body.email()).isPresent()) {
            logger.warn("Registro falhou: e-mail já cadastrado {}", body.email());
            throw new BusinessException("E-mail já cadastrado");
        }
        UserEnt newUser = new UserEnt();
        newUser.setEmail(body.email());
        newUser.setSenha(passwordEncoder.encode(body.senha()));
        newUser.setCpf(body.cpf());
        newUser.setRoles(Collections.singletonList("USER"));
        repository.save(newUser);
        String token = tokenService.generateToken(newUser);
        logger.info("Registro bem-sucedido para {}", body.email());
        return new ResponseDto(newUser.getEmail(), token);
    }
}