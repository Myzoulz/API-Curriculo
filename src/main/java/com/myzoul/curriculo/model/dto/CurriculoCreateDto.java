package com.myzoul.curriculo.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CurriculoCreateDto(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern
                (regexp = "\\d{11}") String cpf,
        @NotNull
        LocalDate dataNascimento,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String escolaridade,
        @NotBlank
        String funcao,
        @NotNull
        @Size
                (min = 1) List<@Valid CompetenciaDto> competencias
) {}
