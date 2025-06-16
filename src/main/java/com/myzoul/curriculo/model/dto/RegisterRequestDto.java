package com.myzoul.curriculo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf
) {}